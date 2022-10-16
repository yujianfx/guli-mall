package cloud.stackexplode.gulimall.search.controller;

import cloud.stackexplode.gulimall.common.utils.R;
import cloud.stackexplode.gulimall.search.entity.SkuEsModel;
import cloud.stackexplode.gulimall.search.service.EsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RequestMapping("/search/")
@RestController
public class EsProductController {
  @Autowired private EsProductService esProductService;
  // 保存到es
  @PostMapping("/save/product")
  public R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels) {
    boolean b = true;
    try {
      b = esProductService.saveProductBatch(skuEsModels);
    } catch (IOException e) {
      e.printStackTrace();
      return R.error(500, "es异常保存失败："+e.getMessage());
    }
    return b ? R.ok() : R.error(500, "保存失败");
  }
}
