
/**
 * @(#) UserService.class $version 2018. 07. 05
 *
 * Copyright 2007 NAVER Corp. All rights Reserved.
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.juhyung.jstagram.user.service;

import com.juhyung.jstagram.user.mapper.UserMapper;
import com.juhyung.jstagram.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UserService
 *
 * @author yun.juhyung@nts-corp.com
 */
@AllArgsConstructor
@Service
public class UserService {
    public final UserMapper userMapper;

    public User getUser(final int userKey) {
        return userMapper.selectUser(userKey);
    }

    public List<User> getUsers(@PathVariable String nickName) {
        return userMapper.selectUsers(nickName);
    }

    public void joinUser(@RequestBody User user) {
        userMapper.insertUser(user);
    }

    public void leaveUser(@PathVariable int userKey) {
        userMapper.deleteUser(userKey);
    }

}