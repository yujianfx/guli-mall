package cloud.stackexplode.gulimall.product.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AttrGroupServiceImplTest {
  @Autowired private AttrGroupServiceImpl attrGroupService;

  @Test
  void queryPageWithAllAttrs() {
    System.out.println(attrGroupService.queryPageWithAllAttrs(new HashMap<>(), 225L));
  }
}
