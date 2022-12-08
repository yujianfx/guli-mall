package cloud.stackexplode.gulimall.search.controller.view;

import cloud.stackexplode.gulimall.search.service.EsSearchService;
import cloud.stackexplode.gulimall.search.vo.SearchParam;
import cloud.stackexplode.gulimall.search.vo.SearchParamVo;
import cloud.stackexplode.gulimall.search.vo.SearchResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("search")
@Controller
@Slf4j
public class SearchViewController {
  @Autowired private EsSearchService esSearchService;

  @GetMapping("/searchView")
  public String searchPage(Model model, SearchParamVo searchParamVo) throws JsonProcessingException {
    SearchResult result = esSearchService.getSearchResult(new SearchParam(searchParamVo));
    model.addAttribute("result", result);

    return "search";
  }
}
