package cloud.stackexplode.gulimall.shoppingcart.service.impl;

import cloud.stackexplode.gulimall.common.constant.CartConstant;
import cloud.stackexplode.gulimall.common.to.product.SkuInfoTo;
import cloud.stackexplode.gulimall.common.to.shoppingcart.to.UserInfoTo;
import cloud.stackexplode.gulimall.common.utils.R;
import cloud.stackexplode.gulimall.common.vo.shoppingcart.vo.CartItemVo;
import cloud.stackexplode.gulimall.common.vo.shoppingcart.vo.CartVo;
import cloud.stackexplode.gulimall.common.vo.shoppingcart.vo.SkuInfoVo;
import cloud.stackexplode.gulimall.shoppingcart.feign.ProductFeignService;
import cloud.stackexplode.gulimall.shoppingcart.interceptor.CartInterceptor;
import cloud.stackexplode.gulimall.shoppingcart.service.CartService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

@Service("CartService")
public class CartServiceImpl implements CartService {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ProductFeignService productFeignService;

    @Autowired
    private ThreadPoolExecutor executor;

    @Override
    public void addCartItem(Long skuId, Integer num) {
        BoundHashOperations<String, String, CartItemVo> ops = getCartItemOps();
        // 判断当前商品是否已经存在购物车
        CartItemVo itemVo = ops.get(skuId.toString());
        if (ObjectUtils.isNotEmpty(itemVo)) {
            ops.increment(skuId.toString(), num);
        } else {
            CartItemVo cartItemVo = new CartItemVo();
            CompletableFuture<Void> cartVoFuture = CompletableFuture.runAsync(() -> {
                //2.1 远程查询sku基本信息
                R<SkuInfoTo> info = productFeignService.info(skuId);
                SkuInfoVo skuInfo = new SkuInfoVo();
                BeanUtils.copyProperties(info.getData(), skuInfo);
                cartItemVo.setCheck(true);
                cartItemVo.setCount(num);
                cartItemVo.setImage(skuInfo.getSkuDefaultImg());
                cartItemVo.setPrice(skuInfo.getPrice());
                cartItemVo.setSkuId(skuId);
                cartItemVo.setTitle(skuInfo.getSkuTitle());
            }, executor);

            //2.2 远程查询sku属性组合信息
            CompletableFuture<Void> skuAttrsFuture = CompletableFuture.runAsync(() -> {
                List<String> attrValuesAsString = productFeignService.getSkuSaleAttrValuesAsString(skuId).getData();
                cartItemVo.setSkuAttrValues(attrValuesAsString);
            }, executor);

            try {
                CompletableFuture.allOf(cartVoFuture, skuAttrsFuture).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            ops.put(skuId.toString(), cartItemVo);
        }
    }

    @Override
    public CartItemVo getCartItem(Long skuId) {
        BoundHashOperations<String, String, CartItemVo> cartItemOps = getCartItemOps();
        return cartItemOps.get(skuId.toString());
    }

    @Override
    public CartVo getCart() {
        CartVo cartVo = new CartVo();
        UserInfoTo userInfoTo = CartInterceptor.threadLocal.get();
        getCartByKey(userInfoTo.getUserId().toString()).ifPresent(cartItemVos -> cartVo.setItems(cartItemVos));
        return cartVo;
    }

    @Override
    public void checkCart(Long skuId) {
        BoundHashOperations<String, String, CartItemVo> ops = getCartItemOps();
        CartItemVo cartItemVo = ops.get(skuId.toString());
        assert cartItemVo != null;
        cartItemVo.setCheck(!cartItemVo.getCheck());
        ops.put(skuId.toString(), cartItemVo);
    }

    @Override
    public void changeItemCount(Long skuId, Integer num) {
        BoundHashOperations<String, String, CartItemVo> ops = getCartItemOps();
        CartItemVo cartItemVo = ops.get(skuId.toString());
        assert cartItemVo != null;
        cartItemVo.setCount(num);
        ops.put(skuId.toString(), cartItemVo);
    }

    @Override
    public void deleteItem(Long skuId) {
        BoundHashOperations<String, String, CartItemVo> ops = getCartItemOps();
        ops.delete(skuId.toString());
    }

    @Override
    public Optional<List<CartItemVo>> getCheckedItems() {
        UserInfoTo userInfoTo = CartInterceptor.threadLocal.get();
        List<CartItemVo> cartByKey = getCartByKey(userInfoTo.getUserId().toString()).get();
        return Optional.of(cartByKey.stream().filter(CartItemVo::getCheck).collect(Collectors.toList()));
    }

    private Optional<List<CartItemVo>> getCartByKey(String userId) {
        BoundHashOperations<String, Object, Object> ops = redisTemplate.boundHashOps(CartConstant.CART_PREFIX + userId);
        List<Object> values = ops.values();
        if (values != null && values.size() > 0) {
            return Optional.of(values.stream().map(obj -> (CartItemVo) obj).collect(Collectors.toList()));
        }
        return Optional.empty();
    }

    private BoundHashOperations<String, String, CartItemVo> getCartItemOps() {
        //1判断是否已经登录
        UserInfoTo userInfoTo = CartInterceptor.threadLocal.get();
        //1.1 登录使用userId操作redis
        return redisTemplate.boundHashOps(CartConstant.CART_PREFIX + userInfoTo.getUserId());

    }
}
