package com.juhyung.board.post.mapper;

import com.juhyung.board.post.model.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {
    @Select("SELECT " +
            "p.id AS id " +
            ", p.writer AS writer " +
            ", p.text AS text " +
            ", p.like_count AS likeCount " +
            ", p.register_date AS registerDate " +
            ", CASE " +
                "(SELECT COUNT(*) " +
                "FROM user_like_post " +
                "WHERE user_key = #{key} " +
                "AND post_id = p.id)  " +
            "WHEN 1 THEN TRUE " +
            "ELSE FALSE END AS liked " +
            "FROM post p ")
    List<Post> selectPosts(int key);

    @Select({"SELECT " +
            "id AS id " +
            ", writer AS writer " +
            ", text AS text " +
            ", like_count AS likeCount " +
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

    @Update({"UPDATE post " +
            "SET like_count = like_count + 1 " +
            "WHERE id = #{id}"})
    void updateLikeCount(final int id);

    @Update({"UPDATE post " +
            "SET like_count = like_count - 1 " +
            "WHERE id = #{id}"})
    void updateUnLikeCount(final int id);

    @Insert({"INSERT INTO user_like_post (post_id, user_key) " +
            "VALUES (#{id}, #{key})"})
    int insertUserLikePost(@Param("id") final int id, @Param("key") final int key);

    @Delete({"DELETE FROM user_like_post " +
            "WHERE post_id = #{id} " +
            "AND user_key = #{key}"})
    int deleteUserLikePost(@Param("id") final int id, @Param("key") final int key);
}
