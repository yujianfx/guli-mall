package cloud.stackexplode.gulimall.search.dao;

import cloud.stackexplode.gulimall.search.entity.SkuEsModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsProductDao extends ElasticsearchRepository<SkuEsModel, Long> {


}
