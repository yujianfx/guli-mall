package cloud.stackexplode.gulimall.search.service;

import cloud.stackexplode.gulimall.search.vo.SearchParam;
import cloud.stackexplode.gulimall.search.vo.SearchResult;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface EsSearchService {
  SearchResult getSearchResult(SearchParam searchParam) throws JsonProcessingException;

}
