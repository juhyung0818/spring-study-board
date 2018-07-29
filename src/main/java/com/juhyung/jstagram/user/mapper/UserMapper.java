package com.juhyung.jstagram.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.juhyung.jstagram.user.model.User;

@Mapper
public interface UserMapper {
    @Select({
            "SELECT " +
            "user_key AS userKey " +
            ", name AS name " +
            ", nick_name AS nickName " +
            "FROM user " +
            "WHERE user_key = #{userKey}"
    })
    User selectUser(final int userKey); //특정 user 정보보기

    @Select({
            "SELECT " +
            "user_key AS userKey " +
            ", name AS name " +
            ", nick_name AS nickName " +
            "FROM user " +
            "WHERE id = #{email}"
    })
    User selectUserByEmail(final String email); //특정 user 정보보기

    @Select({
            "SELECT " +
            "user_key AS userKey " +
            ", name AS name " +
            ", nick_name AS nickName " +
            "FROM user " +
            "WHERE nick_name = #{nickName}"
    })
    User selectUserByNickName(final String nickName); //특정 user 정보보기

    @Select({
            "SELECT " +
            "user_key AS userKey " +
            ", name AS name " +
            ", nick_name AS nickName " +
            "FROM user " +
            "WHERE nick_name LIKE CONCAT(#{nickName}, '%')"
    })
    List<User> selectUsers(final String nickName);

    @Select({
            "SELECT " +
            "user_key AS userKey " +
            ", name AS name " +
            ", nick_name AS nickName " +
            ", id AS id " +
            "FROM user " +
            "WHERE id = #{id} " +
            "AND password = #{password} "
    })
    User selectLoginUser(@Param("id") final String id, @Param("password") final String password);

    @Insert({
            "INSERT INTO user " +
            "(id, password, name, nick_name) " +
            "VALUES " +
            "(#{id}, #{password}, #{name}, #{nickName})"
    })
    void insertUser(final User user);

    @Delete({
            "DELETE FROM user " +
            "WHERE user_key = #{userKey}"
    })
    void deleteUser(final int userKey);

    void updateUser(final User user);
}
