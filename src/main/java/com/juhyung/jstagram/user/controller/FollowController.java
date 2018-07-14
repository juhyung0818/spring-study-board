package com.juhyung.jstagram.user.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juhyung.jstagram.user.model.User;
import com.juhyung.jstagram.user.service.FollowService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/java/follows")
public class FollowController {
    private final FollowService followService;

    //나의 followers
    @GetMapping("/my")
    public List<User> getFollowers(User user) {
        return followService.getFollowers(user);
    }

    //내가 follow 하는 사람들
    @GetMapping("/me")
    public List<User> getFollowings(User user) {
        return followService.getFollowings(user);
    }

    @PostMapping
    public void requestFollow(int me, int you) {
        followService.requestFollow(me, you);
    }

    //TODO
    @DeleteMapping // ?me=1&you=3
    public void cancelFollow(int me, int you) {
        followService.cancelFollow(me, you);
    }
}