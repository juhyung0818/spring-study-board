package com.juhyung.jstagram.user.login.controller;

import com.juhyung.jstagram.user.login.service.LoginService;
import com.juhyung.jstagram.user.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/java/login")
public class LoginController {
    private static final String JSTAGRAM_LOGIN_KEY = "JSTAGRAM_LOGIN";
    private static final String JSTAGRAM_LOGIN_VALUE = "JSTAGRAM_OK";

    private final LoginService loginService;

    @GetMapping
    public String loginView() {
        return "user/login";
    }

    @PostMapping
    @ResponseBody
    public void login(@RequestBody User user, HttpServletResponse response) {
        loginService.login(user.getId(), user.getPassword());
        generateLoginCookie(response);
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public void illegalStateExceptionHandler(IllegalStateException exception) {
        log.error(exception.getMessage() + exception);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public void illegalArgumentException(IllegalArgumentException exception) {
        log.error(exception.getMessage() + exception);
    }

    private void generateLoginCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(JSTAGRAM_LOGIN_KEY, JSTAGRAM_LOGIN_VALUE);
        cookie.setMaxAge(60 * 60);
        response.addCookie(cookie);
    }
}
