package com.juhyung.jstagram.user.controller;

import com.juhyung.jstagram.user.model.User;
import com.juhyung.jstagram.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/java/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{userKey}")
    public User getUser(@PathVariable int userKey) {
        return userService.getUser(userKey);
    }

    @GetMapping({"/search/{nickName}"})
    public List<User> getUsers(@PathVariable String nickName) {
        return userService.getUsers(nickName);
    }

    @PostMapping
    public void joinUser(@RequestBody User user) {
        userService.joinUser(user);
    }

    @DeleteMapping("/{userKey}")
    public void leaveUser(@PathVariable int userKey) {
        userService.leaveUser(userKey);
    }
}