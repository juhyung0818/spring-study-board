package com.juhyung.board.comment.mapper;

import com.juhyung.board.comment.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Select({"SELECT " +
            "id " +
            ", post_id" +
            ", user_key" +
            ", content" +
            ", parent" +
            ", register_date AS registerDate " +
            "FROM comment " +
            "WHERE post_id = #{id} " +
            "AND parent = 0 "})
    List<Comment> selectComments(final int id);

    @Select({"SELECT " +
            "id " +
            ", post_id" +
            ", user_key" +
            ", content" +
            ", parent" +
            ", register_date AS registerDate " +
            "FROM comment " +
            "WHERE post_id = #{id} " +
            "AND parent <> 0 "})
    List<Comment> selectChildComments(final int id);

    @Insert({"INSERT INTO comment (post_id, user_key, content, parent, register_date) " +
            "VALUE (#{postId}, #{userKey}, #{content}, #{parent}, NOW())"})
    void insert(final Comment comment);
}