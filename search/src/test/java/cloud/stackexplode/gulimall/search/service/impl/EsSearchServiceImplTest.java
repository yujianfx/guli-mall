package cloud.stackexplode.gulimall.search.service.impl;

import cloud.stackexplode.gulimall.search.service.EsSearchService;
import cloud.stackexplode.gulimall.search.vo.SearchParam;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class EsSearchServiceImplTest {
  @Autowired private EsSearchService esSearchService;

  @Test
  void getSearchResult() throws JsonProcessingException {
    SearchParam searchParam = new SearchParam();
    log.info(new ObjectMapper().writeValueAsString(esSearchService.getSearchResult(searchParam)));
  }
}
