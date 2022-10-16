package cloud.stackexplode.gulimall.search.service.impl;

import cloud.stackexplode.gulimall.search.service.EsProductService;
import cloud.stackexplode.gulimall.search.service.EsSearchService;
import cloud.stackexplode.gulimall.search.vo.SearchParam;
import cloud.stackexplode.gulimall.search.vo.SearchResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class EsSearchServiceImpl implements EsSearchService {
  @Autowired private EsProductService esProductService;

  @Override
  public SearchResult getSearchResult(SearchParam searchParam) throws JsonProcessingException {
    log.info("searchParam:{}", new ObjectMapper().writeValueAsString(searchParam));
    return esProductService.search(searchParam);
  }
}
