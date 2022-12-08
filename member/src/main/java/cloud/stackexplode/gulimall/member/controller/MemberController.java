package cloud.stackexplode.gulimall.member.controller;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.R;
import cloud.stackexplode.gulimall.common.utils.StatusCode;
import cloud.stackexplode.gulimall.common.entities.member.entity.MemberEntity;
import cloud.stackexplode.gulimall.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * 会员
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-12 20:32:53
 */
@RestController
@RequestMapping("member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = memberService.queryPage(params);

        return R.ok(page);
    }

    //注册
    @PostMapping("/register")
    public R<MemberEntity> register(@RequestBody MemberEntity memberEntity) {
        try {
            return memberService.register(memberEntity) ? R.ok() : R.error();
        } catch (Exception e) {
            return R.error(StatusCode.ERROR, e.getMessage());
        }
    }

    //登录
    @PostMapping("/login")
    public R<MemberEntity> login(@RequestBody MemberEntity memberEntity) {
        try {
            MemberEntity login = memberService.login(memberEntity);
            if (login != null) {
                return R.ok(login);
            } else {
                return R.error(StatusCode.USERNAME_OR_PASSWORD_ERROR, "用户名或密码错误");
            }
        } catch (Exception e) {
            return R.error(StatusCode.ERROR, e.getMessage());
        }
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        MemberEntity member = memberService.getById(id);
        return R.ok(member);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody MemberEntity member) {
        memberService.save(member);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody MemberEntity member) {
        memberService.updateById(member);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        memberService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
}
