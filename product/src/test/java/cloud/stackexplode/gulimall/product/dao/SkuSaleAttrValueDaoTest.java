package cloud.stackexplode.gulimall.product.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class SkuSaleAttrValueDaoTest {
    @Autowired
    private SkuSaleAttrValueDao skuSaleAttrValueDao;

    @Test
    void getSkuSaleAttrValuesAsString() {
        List<String> skuSaleAttrValuesAsString = skuSaleAttrValueDao.getSkuSaleAttrValuesAsString(15L);
        log.info("skuSaleAttrValuesAsString: {}", skuSaleAttrValuesAsString);
    }
}