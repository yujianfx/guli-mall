package top.wangudiercai.gulimall.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.wangudiercai.gulimall.common.utils.PageUtils;
import top.wangudiercai.gulimall.common.utils.Query;

import top.wangudiercai.gulimall.product.dao.CommentReplayDao;
import top.wangudiercai.gulimall.product.entity.CommentReplayEntity;
import top.wangudiercai.gulimall.product.service.CommentReplayService;

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
