package com.community.service;

        import com.community.dao.DiscussPostMapper;
        import com.community.entity.DiscussPost;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;

/**
 * @author stone
 * @version 1.0
 * @ClassName DiscussPostService
 * @Description
 * @date 2020/5/6 22:14
 */


@Service
public class DiscussPostService {
    @Autowired
    DiscussPostMapper discussPostMapper;

    public List<DiscussPost> findDiscussPosts(int userId, int offset, int limit) {
        return discussPostMapper.selectDiscussPosts(userId, offset, limit);
    }

    public int findDiscussPostRows(int userId) {
        return discussPostMapper.selectDiscussPostRows(userId);
    }
}