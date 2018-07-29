package com.juhyung.jstagram.comment.mapper;

import com.juhyung.jstagram.comment.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Select({"SELECT " +
            "comment.id AS id " +
            ", comment.post_id AS postId " +
            ", comment.user_key AS userKey " +
            ", user.nick_name AS writer " +
            ", comment.content AS content " +
            ", comment.parent AS parent " +
            ", comment.register_date AS registerDate " +
            "FROM comment " +
            "INNER JOIN user " +
            "ON user.user_key = comment.user_key " +
            "WHERE comment.post_id = #{id} " +
            "AND comment.parent = 0 "})
    List<Comment> selectComments(final int id);

    @Select({"SELECT " +
            "comment.id AS id " +
            ", comment.post_id AS postId " +
            ", comment.user_key AS userKey " +
            ", user.nick_name AS writer " +
            ", comment.content AS content " +
            ", comment.parent AS parent " +
            ", comment.register_date AS registerDate " +
            "FROM comment " +
            "INNER JOIN user " +
            "ON user.user_key = comment.user_key " +
            "WHERE comment.post_id = #{id} " +
            "AND comment.parent <> 0 "})
    List<Comment> selectChildComments(final int id);

    @Insert({"INSERT INTO comment (post_id, user_key, content, parent, register_date) " +
            "VALUE (#{postId}, #{userKey}, #{content}, #{parent}, NOW())"})
    void insert(final Comment comment);
}