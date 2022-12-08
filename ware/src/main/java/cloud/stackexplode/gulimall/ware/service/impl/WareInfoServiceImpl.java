package cloud.stackexplode.gulimall.ware.service.impl;

import cloud.stackexplode.gulimall.common.entities.member.entity.MemberReceiveAddressEntity;
import cloud.stackexplode.gulimall.common.entities.ware.entity.WareInfoEntity;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.common.vo.order.vo.FareVo;
import cloud.stackexplode.gulimall.common.vo.order.vo.MemberAddressVo;
import cloud.stackexplode.gulimall.ware.dao.WareInfoDao;
import cloud.stackexplode.gulimall.ware.feign.MemberReceiveAddressFeignService;
import cloud.stackexplode.gulimall.ware.service.WareInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service("wareInfoService")
public class WareInfoServiceImpl extends ServiceImpl<WareInfoDao, WareInfoEntity>
        implements WareInfoService {
    @Autowired
    private MemberReceiveAddressFeignService memberReceiveAddressFeignService;

    @Override
    public FareVo getFare(Long addrId, Long wareId) {
        FareVo fareVo = new FareVo();

        MemberAddressVo memberAddressVo = new MemberAddressVo();
        MemberReceiveAddressEntity memberReceiveAddressEntity = memberReceiveAddressFeignService.info(addrId).getData();
        BeanUtils.copyProperties(memberReceiveAddressEntity, memberAddressVo);
        fareVo.setAddress(memberAddressVo);
        fareVo.setWareId(wareId);
        fareVo.setFare(BigDecimal.valueOf(Integer.parseInt(memberAddressVo.getPhone().substring(0,1))));
        //todo 查询运费
        return fareVo;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        QueryWrapper<WareInfoEntity> wareInfoEntityQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(key)) {
            wareInfoEntityQueryWrapper
                    .like("name", key)
                    .or()
                    .like("address", key)
                    .or()
                    .like("areacode", key);
        }
        IPage<WareInfoEntity> page =
                this.page(new Query<WareInfoEntity>().getPage(params), wareInfoEntityQueryWrapper);
        return new PageUtils(page);
    }
}
