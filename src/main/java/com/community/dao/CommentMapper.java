package com.community.dao;

import com.community.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    /**
     * 根据实体类型（帖子、课程、评论）查询某个帖子
     * @param entityType 实体类型（帖子、课程、评论）
     * @param entityId 某个类型的具体的帖子id
     * @param offset 评论起始页
     * @param limit 每页显示多少条评论
     * @return
     */
    List<Comment> selectCommentsByEntity(@Param("entityType")int entityType, @Param("entityId")int entityId, @Param("offset") int offset, @Param("limit") int limit);

    int selectCountByEntity(@Param("entityType") int entityType, @Param("entityId") int entityId);

    int insertComment(Comment comment);

    Comment selectCommentById(int id);
}
