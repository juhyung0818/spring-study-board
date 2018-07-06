
/**
 * @(#) UserService.class $version 2018. 07. 05
 *
 * Copyright 2007 NAVER Corp. All rights Reserved.
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.juhyung.board.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.juhyung.board.user.mapper.FollowMapper;
import com.juhyung.board.user.mapper.UserMapper;
import com.juhyung.board.user.model.User;
import lombok.AllArgsConstructor;

/**
 * UserService
 *
 * @author yun.juhyung@nts-corp.com
 */
@AllArgsConstructor
@Service
public class UserService {
    public final FollowMapper followMapper;
    public final UserMapper userMapper;

    public List<User> getFollowers(final User user) {
        return followMapper.selectFollowers(user);
    }

    public List<User> getFollwings(final User user) {
        return followMapper.selectFollowings(user);
    }

    public void follow(final User me, final User you) {
        followMapper.insertFollow(me, you);
    }

    public void cancelFollow(final User me, final User you) {
        followMapper.deleteFollow(me, you);
    }

    public User getUser(final User user) {
        return userMapper.selectUser(user);
    }

}