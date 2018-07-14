package com.juhyung.jstagram.user.mapper;

import com.juhyung.jstagram.user.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FollowMapper {
    @Select({
            "SELECT "
                    + "user_key As userKey"
                    + ", name AS name"
                    + ", nick_name AS nickName "
                    + "FROM user "
                    + "WHERE user_key IN (SELECT your_key "
                    + "FROM user_follow_user "
                    + "WHERE my_key = #{userKey})"
    })
    List<User> selectFollowers(final int userKey); //나를 팔로우하는 user 목록

    @Select({
            "SELECT "
                    + "user_key As userKey"
                    + ", name AS name"
                    + ", nick_name AS nickName "
                    + "FROM user "
                    + "WHERE user_key IN (SELECT my_key "
                    + "FROM user_follow_user "
                    + "WHERE your_key = #{userKey})"
    })
    List<User> selectFollowings(final int userKey); //내가 팔로우하는 user 목록

    @Insert({
            "INSERT INTO user_follow_user "
                    + "(my_key, your_key) "
                    + "VALUES "
                    + "(#{me}, #{you})"
    })
    void insertFollow(@Param("me") final int me, @Param("you") final int you); //팔로우

    @Delete({
            "DELETE FROM user_follow_user "
                    + "WHERE my_key = #{me} "
                    + "AND your_key = #{you}"
    })
    void deleteFollow(@Param("me") final int me, @Param("you") final int you); //팔로우 취소

}