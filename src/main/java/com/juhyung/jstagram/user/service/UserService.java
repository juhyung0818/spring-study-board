package com.juhyung.jstagram.user.service;

import com.juhyung.jstagram.user.mapper.UserMapper;
import com.juhyung.jstagram.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
    public final UserMapper userMapper;

    public User getUser(final int userKey) {
        return userMapper.selectUser(userKey);
    }

    public User getUserByEmail(final String email) {
        return userMapper.selectUserByEmail(email);
    }

    public User getUserByNickName(final String nickName) {
        return userMapper.selectUserByNickName(nickName);
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