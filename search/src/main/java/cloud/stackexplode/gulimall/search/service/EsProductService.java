package cloud.stackexplode.gulimall.search.service;

import cloud.stackexplode.gulimall.search.entity.SkuEsModel;
import cloud.stackexplode.gulimall.search.vo.SearchParam;
import cloud.stackexplode.gulimall.search.vo.SearchResult;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.List;

/**
 * es产品服务
 *
 * @author 26530
 * @date 2022/10/07
 */
public interface EsProductService {
  /**
   * 保存产品批
   *
   * @param skuEsModels sku es模型
   * @return {@link Boolean}
   * @throws IOException ioexception
   */
  Boolean saveProductBatch(List<SkuEsModel> skuEsModels) throws IOException;

  SearchResult search(SearchParam searchParam) throws JsonProcessingException;
}
