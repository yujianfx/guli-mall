package cloud.stackexplode.gulimall.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AttrServiceImplTest {
    @Autowired
    private AttrServiceImpl attrService;

    @org.junit.jupiter.api.Test
    void queryPageByCid() {
    }

    @org.junit.jupiter.api.Test
    void queryPage() {
    }

    @org.junit.jupiter.api.Test
    void saveAttr() {
    }

    @org.junit.jupiter.api.Test
    void getAttrDetailById() {
        attrService.getById(13);
    }
}
