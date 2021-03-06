package com.juhyung.jstagram.user.login.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {
    private static final String JSTAGRAM_LOGIN_KEY = "JSTAGRAM_LOGIN";
    private static final String JSTAGRAM_LOGIN_VALUE = "JSTAGRAM_OK";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean login = Optional.ofNullable(request.getCookies())
                        .map(cookies -> Stream.of(cookies)
                                .anyMatch(cookie -> cookie.getName().equals(JSTAGRAM_LOGIN_KEY)
                                        && cookie.getValue().equals(JSTAGRAM_LOGIN_VALUE)))
                        .get();
        if (!login) {
            response.sendRedirect("/java/login");
        }

        log.info("login : {}", login);
        return login;
    }
}