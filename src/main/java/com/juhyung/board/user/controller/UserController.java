package com.juhyung.board.user.controller;

import com.juhyung.board.user.service.FollowService;
import com.juhyung.board.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class UserController {
    private final UserService userService;
    private final FollowService followService;
}
