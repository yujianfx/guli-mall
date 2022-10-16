package cloud.stackexplode.gulimall.search.vo;

import cloud.stackexplode.gulimall.search.entity.SkuEsModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchParam {
  public SearchParam(SearchParamVo searchParamVo) {
    BeanUtils.copyProperties(searchParamVo, this);
    List<String> attrsVo = searchParamVo.getAttrsString();
    if (attrsVo != null && attrsVo.size() > 0) {
        this.attrs = attrsVo.stream()
            .map(
                attr -> {
                  String[] s = attr.split("_");
                  SkuEsModel.Attr attrEsModel = new SkuEsModel.Attr();
                  attrEsModel.setAttrId(Long.parseLong(s[0]));
                  attrEsModel.setAttrValue(s[1]);
                  return attrEsModel;
                })
            .collect(Collectors.toList());
    }
  }
  /** 页面传递过来的全文匹配关键字 */
  private String keyword;

  /** 品牌id,可以多选 */
  private List<Long> brandId;

  /** 三级分类id */
  private Long catelog3Id;

  /** 排序条件：sort=price/salecount/hotscore_desc/asc */
  private String sort;

  /** 是否显示有货 */
  private Boolean hasStock;

  /** 价格区间查询 */
  private String skuPrice;

  /** 按照属性进行筛选 */
  private List<SkuEsModel.Attr> attrs;

  /** 页码 */
  private Integer pageNum = 1;

  /** 原生的所有查询条件 */
  private String _queryString;
}
