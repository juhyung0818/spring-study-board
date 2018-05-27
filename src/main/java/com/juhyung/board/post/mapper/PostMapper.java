package com.juhyung.board.post.mapper;

import com.juhyung.board.post.model.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {
    @Select("SELECT " +
            "id AS id " +
            ", title AS title " +
            ", content AS content " +
            ", register_date AS registerDate " +
            ", view_count AS viewCount " +
            "FROM post")
    List<Post> selectPosts();

    @Select({"SELECT " +
            "id AS id " +
            ", title AS title " +
            ", content AS content " +
            ", register_date AS registerDate " +
            ", view_count AS viewCount " +
            " FROM post WHERE id = #{id}"})
    Post selectPost(final int id);

    @Insert({"INSERT INTO post (title, content, register_date, view_count) " +
            "VALUES (#{title}, #{content}, NOW(), #{viewCount})"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(final Post post);

    @Delete({"DELETE FROM post " +
            "WHERE id = #{id}"})
    void delete(final int id);

    @Update({"UPDATE post " +
            "SET view_count = view_count + 1 " +
            "WHERE id = #{id}"})
    void updateViewCount(final int id);

    @Update({"UPDATE post " +
            "SET title=#{title} " +
            ", content = #{content} " +
            "WHERE id = #{id}"})
    void updatePost(final Post post);
}
