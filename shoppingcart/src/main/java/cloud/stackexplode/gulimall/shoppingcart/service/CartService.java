package cloud.stackexplode.gulimall.shoppingcart.service;

import cloud.stackexplode.gulimall.common.vo.shoppingcart.vo.CartItemVo;
import cloud.stackexplode.gulimall.common.vo.shoppingcart.vo.CartVo;

import java.util.List;
import java.util.Optional;

public interface CartService {
    void addCartItem(Long skuId, Integer num);

    CartItemVo getCartItem(Long skuId);

    CartVo getCart();

    void checkCart(Long skuId);

    void changeItemCount(Long skuId, Integer num);

    void deleteItem(Long skuId);

    Optional<List<CartItemVo>> getCheckedItems();
}
