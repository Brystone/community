package com.community.dao;

import com.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author stone
 * @version 1.0
 * @ClassName DiscussPostMapper
 * @Description
 * @date 2020/5/5 16:11
 */
@Mapper
public interface DiscussPostMapper {
    /**
     * 首页查询帖子接口，单纯查询帖子无需userId
     * @param userId:后续扩展查询自己所发的帖子
     * @param offset：使用limit分页查询，起始行
     * @param limit：查询几条数据
     * @return
     */
    List<DiscussPost> selectDiscussPosts(@Param("userId") int userId, @Param("offset") int offset, @Param("limit") int limit);

    //@Param注解,给参数取别名
    //如果只有一个参数，并且在<if>里使用

    /**
     * 查询帖子行数
     * @param userId
     * @return
     */
    int selectDiscussPostRows(@Param("userId") int userId);
}
