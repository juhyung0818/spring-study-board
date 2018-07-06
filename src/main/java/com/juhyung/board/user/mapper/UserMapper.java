/**
 * @(#) UserMapper.class $version 2018. 07. 05
 *
 * Copyright 2007 NAVER Corp. All rights Reserved.
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.juhyung.board.user.mapper;

import com.juhyung.board.user.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * UserMapper
 *
 * @author yun.juhyung@nts-corp.com
 */
@Mapper
public interface UserMapper {
    @Select({
            "SELECT " +
            "user_key" +
            ", name" +
            ", nick_name " +
            "FORM user " +
            "WHERE user_key = #{userKey}"
    })
    User selectUser(final User user); //특정 user 정보보기


    void inserUser(final User user);

    void deleteUser(final User user);

    void updateUser(final User user);
}
