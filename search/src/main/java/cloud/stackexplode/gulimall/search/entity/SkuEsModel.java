package cloud.stackexplode.gulimall.search.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.util.List;

@ToString
@Data
@Document(indexName = "spuinfo", replicas = 1, shards = 2)
public class SkuEsModel {
  @Id private Long skuId;

  @Field(type = FieldType.Long)
  private Long spuId;

  @Field(type = FieldType.Text, analyzer = "ik_smart")
  private String skuTitle;

  @Field(type = FieldType.Double)
  private BigDecimal skuPrice;

  @Field(type = FieldType.Keyword)
  private String skuImg;

  @Field(type = FieldType.Long)
  private Long saleCount;

  @Field(type = FieldType.Boolean)
  private Boolean hasStock;

  @Field(type = FieldType.Long)
  private Long hotScore;

  @Field(type = FieldType.Long)
  private Long brandId;

  @Field(type = FieldType.Long)
  private Long catelogId;

  @Field(type = FieldType.Keyword)
  private String brandName;

  @Field(type = FieldType.Keyword)
  private String brandImg;

  @Field(type = FieldType.Keyword)
  private String catelogName;

  @Field(type = FieldType.Nested)
  private List<Attr> attrs;

  @Data
  public static class Attr {
    @Field(type = FieldType.Long)
    private Long attrId;

    @Field(type = FieldType.Keyword)
    private String attrName;

    @Field(type = FieldType.Keyword)
    private String attrValue;
  }
}
