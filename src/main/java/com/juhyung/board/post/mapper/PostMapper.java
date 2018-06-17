package com.juhyung.board.post.mapper;

import com.juhyung.board.post.model.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {
    @Select("SELECT " +
            "id AS id " +
            ", writer AS writer " +
            ", text AS text " +
            ", register_date AS registerDate " +
            "FROM post")
    List<Post> selectPosts();

    @Select({"SELECT " +
            "id AS id " +
            ", writer AS writer " +
            ", text AS text " +
            ", register_date AS registerDate " +
            " FROM post WHERE id = #{id}"})
    Post selectPost(final int id);

    @Insert({"INSERT INTO post (writer, text, register_date, like_count) " +
            "VALUES (#{writer}, #{text}, NOW(), 0)"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(final Post post);

    @Delete({"DELETE FROM post " +
            "WHERE id = #{id}"})
    void delete(final int id);

    @Update({"UPDATE post " +
            "SET like_count = like_count + 1 " +
            "WHERE id = #{id}"})
    void updateViewCount(final int id);

    @Update({"UPDATE post " +
            "SET writer = #{writer} " +
            ", text = #{text} " +
            "WHERE id = #{id}"})
    void updatePost(final Post post);
}
