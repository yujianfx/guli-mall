package cloud.stackexplode.gulimall.ware.feign;

import cloud.stackexplode.gulimall.common.entities.member.entity.MemberReceiveAddressEntity;
import cloud.stackexplode.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient("member-service")

public interface MemberReceiveAddressFeignService {
    @RequestMapping("member/memberreceiveaddress/info/{id}")
    R<MemberReceiveAddressEntity> info(@PathVariable("id") Long id);

}
