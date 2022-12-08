package cloud.stackexplode.gulimall.member.controller;

import cloud.stackexplode.gulimall.common.entities.member.entity.MemberReceiveAddressEntity;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.R;
import cloud.stackexplode.gulimall.member.service.MemberReceiveAddressService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 会员收货地址
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-12 20:32:53
 */
@RestController
@RequestMapping("member/memberreceiveaddress")
public class MemberReceiveAddressController {
    @Autowired
    private MemberReceiveAddressService memberReceiveAddressService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = memberReceiveAddressService.queryPage(params);

        return R.ok(page);
    }

    @RequestMapping("/infoByUserId/{userId}")
    R<List<MemberReceiveAddressEntity>> getAddressByUserId(@PathVariable("userId") Long userId) {
        List<MemberReceiveAddressEntity> addressEntities = memberReceiveAddressService.getAllByUserId(userId);
        return ObjectUtils.allNotNull(addressEntities) ? R.ok(addressEntities) : R.error("还没有添加收货地址哦");
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R<MemberReceiveAddressEntity> info(@PathVariable("id") Long id) {
        MemberReceiveAddressEntity memberReceiveAddress = memberReceiveAddressService.getById(id);
        return ObjectUtils.allNotNull(memberReceiveAddress) ? R.ok(memberReceiveAddress) : R.error("No data found");
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody MemberReceiveAddressEntity memberReceiveAddress) {
        memberReceiveAddressService.save(memberReceiveAddress);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody MemberReceiveAddressEntity memberReceiveAddress) {
        memberReceiveAddressService.updateById(memberReceiveAddress);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        memberReceiveAddressService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
}
