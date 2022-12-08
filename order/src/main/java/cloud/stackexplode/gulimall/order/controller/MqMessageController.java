package cloud.stackexplode.gulimall.order.controller;

import cloud.stackexplode.gulimall.common.to.mq.StockLockedTo;
import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.R;
import cloud.stackexplode.gulimall.common.entities.order.entity.MqMessageEntity;
import cloud.stackexplode.gulimall.order.service.MqMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-12 20:44:00
 */
@RestController
@RequestMapping("order/mqmessage")
public class MqMessageController {
    @Autowired
    private MqMessageService mqMessageService;

    /**
     * 列表
     */
    @RequestMapping("/list")

    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = mqMessageService.queryPage(params);

        return R.ok(page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{messageId}")
    public R info(@PathVariable("messageId") String messageId) {
        MqMessageEntity mqMessage = mqMessageService.getById(messageId);

        return R.ok(mqMessage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody MqMessageEntity mqMessage) {
        mqMessageService.save(mqMessage);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody MqMessageEntity mqMessage) {
        mqMessageService.updateById(mqMessage);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] messageIds) {
        mqMessageService.removeByIds(Arrays.asList(messageIds));

        return R.ok();
    }
}
