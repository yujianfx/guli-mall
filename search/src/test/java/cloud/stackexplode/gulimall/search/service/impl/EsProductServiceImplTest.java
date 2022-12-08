package cloud.stackexplode.gulimall.search.service.impl;

import cloud.stackexplode.gulimall.search.entity.SkuEsModel;
import cloud.stackexplode.gulimall.search.vo.SearchParam;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class EsProductServiceImplTest {

  @Autowired private EsProductServiceImpl esProductService;

  @Test
  void search() throws JsonProcessingException {
    SearchParam searchParam = new SearchParam();
    //    searchParam.setBrandId(Arrays.asList(1L));
    //        searchParam.setSkuPrice("1_1001");
    //    searchParam.setHasStock(true);

//    ArrayList<SkuEsModel.Attr> attrs = new ArrayList<>();
//    SkuEsModel.Attr attr = new SkuEsModel.Attr();
//    attr.setAttrId(6L);
//    attr.setAttrValue("套餐一");
//    attrs.add(attr);
//    searchParam.setAttrs(attrs);
    searchParam.setKeyword("钛金");
    searchParam.setSort("skuPrice_desc");
    searchParam.setPageNum(1);
    esProductService.search(searchParam);
  }

  // 生成数据批量保存
  @Test
  public void saveBatch() throws IOException {
    List<SkuEsModel> esModels = new ArrayList<>();
    SkuEsModel skuEsModel = new SkuEsModel();
    skuEsModel.setSkuId(111L);
    skuEsModel.setSkuTitle("华为手机");
    skuEsModel.setSkuPrice(new BigDecimal("1000"));
    skuEsModel.setBrandId(1L);
    skuEsModel.setBrandName("华为");
    skuEsModel.setCatelogId(225L);
    skuEsModel.setCatelogName("手机");
    skuEsModel.setHasStock(true);
    skuEsModel.setSkuImg("http://www.baidu.com");
    esModels.add(skuEsModel);
    SkuEsModel skuEsModel1 = new SkuEsModel();
    skuEsModel1.setSkuId(112L);
    skuEsModel1.setSkuTitle("垃圾手机");
    skuEsModel1.setSkuPrice(new BigDecimal("1000"));
    skuEsModel1.setBrandId(1L);
    skuEsModel1.setBrandName("华为");
    skuEsModel1.setCatelogId(225L);
    skuEsModel1.setCatelogName("手机");
    skuEsModel1.setHasStock(true);
    skuEsModel1.setSkuImg("http://www.baidu.com");
    esModels.add(skuEsModel1);
    esProductService.saveProductBatch(esModels);
  }
}
