package cloud.stackexplode.gulimall.order.feign;


import cloud.stackexplode.gulimall.common.utils.R;
import cloud.stackexplode.gulimall.common.vo.order.vo.MemberAddressVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@FeignClient("member-service")
@ResponseBody
public interface MemberFeignService {

    @GetMapping("member/memberreceiveaddress/infoByUserId/{userId}")
    R<List<MemberAddressVo>> getAddressByUserId(@PathVariable("userId") Long userId);
}
