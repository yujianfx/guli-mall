package top.wangudiercai.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-10 18:27:08
 */
public interface CategoryService extends IService<CategoryEntity> {

  PageUtils queryPage(Map<String, Object> params);

  List<CategoryEntity> listTree();
}
