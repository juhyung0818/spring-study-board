/**
 * @(#) FollowMapper.class $version 2018. 07. 05
 *
 * Copyright 2007 NAVER Corp. All rights Reserved.
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.juhyung.board.user.mapper;

import com.juhyung.board.user.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * FollowMapper
 *
 * @author yun.juhyung@nts-corp.com
 */
public interface FollowMapper {
    @Select({
            "SELECT u.key, u.name, u.nick_name "
            + "FROM user u"
            + "JOIN user_follow_user ufu "
            + "ON ufu.your_key = u.userKey "
            + "WHERE u.user_key = #{userKey}"
    })
    List<User> selectFollowers(final User user); //나를 팔로우하는 user 목록

    @Select({
            "SELECT u.key, u.name, u.nickName "
            + "FROM user u"
            + "JOIN user_follow_user ufu "
            + "ON ufu.my_key = u.key "
            + "WHERE u.key = #{userKey}"
    })
    List<User> selectFollowings(final User user); //내가 팔로우하는 user 목록

    @Insert({
            "INSERT INTO user_follow_user "
            + "(my_key, your_key) "
            + "VALUES "
            + "(#{me.userKey}, #{you,userKey})"
    })
    void insertFollow(@Param("me") final User me, @Param("you") final User you); //팔로우

    @Delete({
            "DELETE FROM user_follow_user "
            + "WHERE my_key = #{me.userKey} "
            + "AND your_key = #{you.userKey})"
    })
    void deleteFollow(@Param("me") final User me, @Param("you") final User you); //팔로우 취소

}