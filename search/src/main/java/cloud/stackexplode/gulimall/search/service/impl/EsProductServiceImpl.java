package cloud.stackexplode.gulimall.search.service.impl;

import cloud.stackexplode.gulimall.search.dao.EsProductDao;
import cloud.stackexplode.gulimall.search.entity.SkuEsModel;
import cloud.stackexplode.gulimall.search.service.EsProductService;
import cloud.stackexplode.gulimall.search.vo.SearchParam;
import cloud.stackexplode.gulimall.search.vo.SearchResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.nested.ParsedNested;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.support.ValueType;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EsProductServiceImpl implements EsProductService {
  @Autowired private EsProductDao esProductDao;
  @Autowired private ElasticsearchRestTemplate elasticsearchRestTemplate;

  @Value("${spring.data.elasticsearch.pageable.pagesize}")
  private Integer pageSize;

  @Override
  public Boolean saveProductBatch(List<SkuEsModel> skuEsModels) throws IOException {
    boolean res = true;
    try {
      esProductDao.saveAll(skuEsModels);
    } catch (Exception e) {
      res = false;
      log.error(e.getMessage());
    } finally {
      log.info("保存结束");
    }
    return res;
  }

  @Override
  public SearchResult search(SearchParam searchParam) throws JsonProcessingException {
    NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
    String keyword = searchParam.getKeyword();
    List<Long> brandId = searchParam.getBrandId();
    Long cateLogId = searchParam.getCatelog3Id();
    String sort = searchParam.getSort();
    Boolean hasStock = searchParam.getHasStock();
    String skuPrice = searchParam.getSkuPrice();
    List<SkuEsModel.Attr> attrs = searchParam.getAttrs();
    Integer pageNum = searchParam.getPageNum();
    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
    if (keyword != null && keyword.length() > 0) {
      boolQueryBuilder.must(QueryBuilders.matchQuery("skuTitle", keyword));
    }
    if (brandId != null && brandId.size() > 0) {
      boolQueryBuilder.must(QueryBuilders.termsQuery("brandId", brandId));
    }
    if (cateLogId != null && cateLogId > 0) {
      boolQueryBuilder.must(QueryBuilders.termQuery("catelogId", cateLogId));
    }
    if (hasStock != null) {
      boolQueryBuilder.must(QueryBuilders.termQuery("hasStock", hasStock));
    }
    if (skuPrice != null && skuPrice.length() > 0) {
      String[] price = skuPrice.split("_");
      if (price.length == 2) {
        boolQueryBuilder.must(QueryBuilders.rangeQuery("skuPrice").gte(price[0]).lte(price[1]));
      }
    }
    if (attrs != null && attrs.size() > 0) {
      for (SkuEsModel.Attr attr : attrs) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.termQuery("attrs.attrId", attr.getAttrId()));
        boolQuery.must(QueryBuilders.matchQuery("attrs.attrValue", attr.getAttrValue()));
        boolQueryBuilder.must(QueryBuilders.nestedQuery("attrs", boolQuery, ScoreMode.Max));
      }
    }
    String sortField = "saleCount";
    String sortOrder = "asc";
    if (StringUtils.isNotEmpty(sort)) {
      sortField = sort.split("_")[0];
      sortOrder = sort.split("_")[1];
      if (sortField.contains("skuPrice")) {
        sortField = "skuPrice";
      }
      if (sortField.contains("saleCount")) {
        sortField = "saleCount";
      }
      if (sortField.contains("hotsCore")) {
        sortField = "hotScore";
      }
    }

    HighlightBuilder highlightBuilder = new HighlightBuilder();
    highlightBuilder.field("skuTitle");
    highlightBuilder.preTags("<b style='color:red'>").postTags("</b>");
    TermsAggregationBuilder brands =
        AggregationBuilders.terms("brand_agg").field("brandId").valueType(ValueType.LONG);
    brands.subAggregation(
        AggregationBuilders.terms("brand_name_agg").field("brandName").valueType(ValueType.STRING));
    brands.subAggregation(
        AggregationBuilders.terms("brand_img_agg").field("brandImg").valueType(ValueType.STRING));

    TermsAggregationBuilder catelog =
        AggregationBuilders.terms("catelog_agg").field("catelogId").valueType(ValueType.LONG);

    catelog.subAggregation(
        AggregationBuilders.terms("catelog_name_agg")
            .field("catelogName")
            .valueType(ValueType.STRING));

    NestedAggregationBuilder attrsAgg = AggregationBuilders.nested("attr_agg", "attrs");
    attrsAgg.subAggregation(
        AggregationBuilders.terms("attr_id_agg")
            .valueType(ValueType.LONG)
            .field("attrs.attrId")
            .subAggregation(
                AggregationBuilders.terms("attr_name_agg")
                    .field("attrs.attrName")
                    .valueType(ValueType.STRING))
            .subAggregation(AggregationBuilders.terms("attr_value_agg").field("attrs.attrValue"))
            .valueType(ValueType.STRING));

    NativeSearchQuery nativeSearchQuery =
        queryBuilder
            .addAggregation(brands)
            .addAggregation(catelog)
            .addAggregation(attrsAgg)
            .withQuery(boolQueryBuilder)
            .withPageable(PageRequest.of(pageNum - 1, pageSize))
            .withHighlightBuilder(highlightBuilder)
            .withSort(
                SortBuilders.fieldSort(sortField)
                    .order("asc".equals(sortOrder) ? SortOrder.ASC : SortOrder.DESC))
            .build();
    log.info(nativeSearchQuery.toString());
    SearchHits<SkuEsModel> searchHits =
        elasticsearchRestTemplate.search(nativeSearchQuery, SkuEsModel.class);
    List<SkuEsModel> skuEsModels = new ArrayList<>();
    List<SearchHit<SkuEsModel>> searchHitList = searchHits.getSearchHits();
    searchHitList.forEach(
        searchHit -> {
          SkuEsModel skuEsModel = searchHit.getContent();
          String skuTitle =
              searchHit.getHighlightField("skuTitle").stream().collect(Collectors.joining());
          skuEsModel.setSkuTitle(
              StringUtils.isNotEmpty(skuTitle) ? skuTitle : skuEsModel.getSkuTitle());
          skuEsModels.add(skuEsModel);
        });
    Aggregations aggregations = searchHits.getAggregations();
    List<SearchResult.CatelogVo> catelogVos = new ArrayList<>();
    List<SearchResult.BrandVo> brandVos = new ArrayList<>();
    List<SearchResult.AttrVo> attrVos = new ArrayList<>();
    List<SearchResult.NavVo> navVos = new ArrayList<>();
    Terms brandAgg = aggregations.get("brand_agg");
    List<? extends Terms.Bucket> brandBuckets = brandAgg.getBuckets();
    brandBuckets.forEach(
        b -> {
          SearchResult.BrandVo brandVo = new SearchResult.BrandVo();
          Aggregations subAggregations = b.getAggregations();
          Terms brandNameAgg = subAggregations.get("brand_name_agg");
          Terms brandImgAgg = subAggregations.get("brand_img_agg");
          brandVo.setBrandId(b.getKeyAsNumber().longValue());
          if (brandNameAgg.getBuckets().size() > 0) {
            brandVo.setBrandName(brandNameAgg.getBuckets().get(0).getKeyAsString());
          } else {
            brandVo.setBrandName("未找到");
          }
          if (brandImgAgg.getBuckets().size() > 0) {
            brandVo.setBrandImg(
                StringUtils.isBlank(brandImgAgg.getBuckets().get(0).getKeyAsString())
                    ? "默认图片"
                    : brandImgAgg.getBuckets().get(0).getKeyAsString());
          } else {
            brandVo.setBrandImg("默认图片");
          }
          brandVos.add(brandVo);
        });
    Terms catelogAgg = aggregations.get("catelog_agg");
    List<? extends Terms.Bucket> catelogBuckets = catelogAgg.getBuckets();
    catelogBuckets.forEach(
        b -> {
          SearchResult.CatelogVo catelogVo = new SearchResult.CatelogVo();
          catelogVo.setCatelogId(b.getKeyAsNumber().longValue());
          Terms catelogNameAgg = b.getAggregations().get("catelog_name_agg");
          if (catelogNameAgg.getBuckets().size() > 0) {
            catelogVo.setCatelogName(catelogNameAgg.getBuckets().get(0).getKeyAsString());
          } else {
            catelogVo.setCatelogName("未知分类");
          }
          catelogVos.add(catelogVo);
        });

    ParsedNested attrAgg = aggregations.get("attr_agg");
    ParsedLongTerms attrIdAgg = attrAgg.getAggregations().get("attr_id_agg");
    attrIdAgg
        .getBuckets()
        .forEach(
            b -> {
              SearchResult.AttrVo attrVo = new SearchResult.AttrVo();
              attrVo.setAttrId(b.getKeyAsNumber().longValue());
              Terms attrNameAgg = b.getAggregations().get("attr_name_agg");
              if (attrNameAgg.getBuckets().size() > 0) {
                attrVo.setAttrName(attrNameAgg.getBuckets().get(0).getKeyAsString());
              } else {
                attrVo.setAttrName("未知属性");
              }
              Terms attrValueAgg = b.getAggregations().get("attr_value_agg");
              List<String> attrValues = new ArrayList<>();
              attrValueAgg.getBuckets().forEach(v -> attrValues.add(v.getKeyAsString()));
              attrVo.setAttrValue(attrValues);
              attrVos.add(attrVo);
            });
    log.info("attrVos:{}", attrVos);
    log.info("catelogVos:{}", catelogVos);
    log.info("brandVos:{}", brandVos);
    log.info("skuEsModels:{}", skuEsModels);
    SearchResult searchResult = new SearchResult();
    searchResult.setAttrs(attrVos);
    searchResult.setBrands(brandVos);
    searchResult.setCatelogs(catelogVos);
    searchResult.setProduct(skuEsModels);
    searchResult.setPageNum(pageNum);
    searchResult.setTotalPages(
        (int)
            (searchHits.getTotalHits() % pageSize == 0
                ? searchHits.getTotalHits() / pageSize
                : searchHits.getTotalHits() / pageSize + 1));
    searchResult.setTotal(searchHits.getTotalHits());
    searchResult.setPageNavs(getPageNavs(pageNum, searchResult.getTotalPages()));
    attrVos.forEach(
        a -> {
          SearchResult.NavVo navVo = new SearchResult.NavVo();
          navVo.setNavName(a.getAttrName());
          navVo.setNavValue(String.join(";", a.getAttrValue()));
          navVo.setLink(
              "http://www.gulimall.com/search?attrId="
                  + a.getAttrId()
                  + "&"
                  + "attrValue="
                  + a.getAttrValue());
          navVos.add(navVo);
        });
    return searchResult;
  }

  private List<Integer> getPageNavs(Integer pageNum, Integer totalPages) {
    List<Integer> pageNavs = new ArrayList<>();
    if (totalPages <= 5) {
      for (int i = 1; i <= totalPages; i++) {
        pageNavs.add(i);
      }
    } else {
      if (pageNum <= 3) {
        pageNavs.add(1);
        pageNavs.add(2);
        pageNavs.add(3);
        pageNavs.add(4);
        pageNavs.add(5);
      } else if (pageNum > totalPages - 3) {
        pageNavs.add(totalPages - 4);
        pageNavs.add(totalPages - 3);
        pageNavs.add(totalPages - 2);
        pageNavs.add(totalPages - 1);
        pageNavs.add(totalPages);
      } else {
        pageNavs.add(pageNum - 2);
        pageNavs.add(pageNum - 1);
        pageNavs.add(pageNum);
        pageNavs.add(pageNum + 1);
        pageNavs.add(pageNum + 2);
      }
    }
    return pageNavs;
  }
}
