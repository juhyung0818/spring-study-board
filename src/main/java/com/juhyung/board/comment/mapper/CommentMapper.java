package com.juhyung.board.comment.mapper;

import com.juhyung.board.comment.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Select({"SELECT " +
            "id" +
            ", content" +
            ", register_date " +
            "FROM comment " +
            "WHERE post_id = #{id} " +
            "ORDER BY register_date desc"})
    List<Comment> selectCommentsByPostId(final int id);

    @Insert({"INSERT INTO comment (post_id, content, register_date) " +
            "VALUE (#{postId}, #{content}, NOW())"})
    void insert(final Comment comment);
}
