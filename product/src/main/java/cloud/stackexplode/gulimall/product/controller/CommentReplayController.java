package cloud.stackexplode.gulimall.product.controller;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.R;
import cloud.stackexplode.gulimall.common.entities.product.entity.CommentReplayEntity;
import cloud.stackexplode.gulimall.product.service.CommentReplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * 商品评价回复关系
 *
 * @author wangudiercai
 * @email 2653084650@qq.com
 * @date 2022-07-10 18:27:08
 */
@RestController
@RequestMapping("product/commentreplay")
public class CommentReplayController {
    @Autowired
    private CommentReplayService commentReplayService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = commentReplayService.queryPage(params);

        return R.ok(page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        CommentReplayEntity commentReplay = commentReplayService.getById(id);

        return R.ok(commentReplay);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody CommentReplayEntity commentReplay) {
        commentReplayService.save(commentReplay);

        return R.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody CommentReplayEntity commentReplay) {
        commentReplayService.updateById(commentReplay);

        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        commentReplayService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
}
