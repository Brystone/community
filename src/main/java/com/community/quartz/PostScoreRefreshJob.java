package com.community.quartz;

import com.community.entity.DiscussPost;
import com.community.service.DiscussPostService;
import com.community.service.ElasticsearchService;
import com.community.service.LikeService;
import com.community.util.CommunityConstant;
import com.community.util.RedisKeyUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName PostScoreRefreshJob
 * @Description
 */
public class PostScoreRefreshJob implements Job, CommunityConstant {
    // 记录日志
    private static final Logger logger  = LoggerFactory.getLogger(PostScoreRefreshJob.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private ElasticsearchService elasticsearchService;

    // 牛客纪元，静态常量
    private static final Date epoch;

    // 静态代码块对 epoch 初始化 使用 SimpleDateFormat 工具对日期格式进行修改
    static {
        try {
            epoch = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-08-01 00:00:00");
        } catch (ParseException e) {
            throw new RuntimeException("牛客初始纪元失败", e);
        }
    }


    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        // 获取key和value操作工具
        String redisKey = RedisKeyUtil.getPostScoreKey();
        BoundSetOperations operations = redisTemplate.boundSetOps(redisKey);

        if (operations.size() == 0) {
            logger.info("[任务取消] 没有需要刷新的帖子");
            return;
        }

        logger.info("[任务开始] 正在刷新帖子:" + operations.size());
        while (operations.size() > 0) {
            this.refresh((Integer)operations.pop());
        }
        logger.info("[任务结束] 刷新结束");
    }

    private void refresh(int postId) {
        DiscussPost post = discussPostService.findDiscussPostById(postId);

        if (post == null) {
            logger.info("该帖子不存在: id = "+postId);
            return;
        }

        // 是否为精华贴
        boolean wonderful = post.getStatus() == 1;
        // 评论数量
        int commentCount = post.getCommentCount();
        // 点赞数量
        long likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_POST, postId);

        // 计算权重
        double w = (wonderful ? 75 : 0) + commentCount * 10 + likeCount * 2;
        // 分数 = 帖子权重 + 天数差
        double score = Math.log10(Math.max(w, 1))
                + (post.getCreateTime().getTime() - epoch.getTime() / (1000 * 3600 * 24));
        // 更新帖子分数
        discussPostService.updateScore(postId, score);
        // 同步es服务器
        post.setScore(score);
        elasticsearchService.saveDiscussPost(post);
    }
}
