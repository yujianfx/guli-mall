package cloud.stackexplode.gulimall.product.service.impl;

import cloud.stackexplode.gulimall.common.utils.PageUtils;
import cloud.stackexplode.gulimall.common.utils.Query;
import cloud.stackexplode.gulimall.product.dao.CommentReplayDao;
import cloud.stackexplode.gulimall.product.entity.CommentReplayEntity;
import cloud.stackexplode.gulimall.product.service.CommentReplayService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("commentReplayService")
public class CommentReplayServiceImpl extends ServiceImpl<CommentReplayDao, CommentReplayEntity>
        implements CommentReplayService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CommentReplayEntity> page =
                this.page(
                        new Query<CommentReplayEntity>().getPage(params),
                        new QueryWrapper<CommentReplayEntity>());

        return new PageUtils(page);
    }
}
