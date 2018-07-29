package com.juhyung.jstagram.user.profile.controller;

import com.juhyung.jstagram.post.service.PostService;
import com.juhyung.jstagram.user.model.User;
import com.juhyung.jstagram.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/java/users")
@AllArgsConstructor
public class ProfileController {
    private final UserService userService;
    private final PostService postService;

    @GetMapping("/{nickName}")
    public String getProfileView(@PathVariable String nickName, Model model) {
        User user = userService.getUserByNickName(nickName);
        model.addAttribute("user", user);
        model.addAttribute("posts", postService.getPosts(user.getUserKey()));
        return "/user/profile";
    }

}
