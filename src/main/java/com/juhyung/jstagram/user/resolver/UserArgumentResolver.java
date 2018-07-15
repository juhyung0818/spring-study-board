package com.juhyung.jstagram.user.resolver;

import com.juhyung.jstagram.common.annotation.Login;
import com.juhyung.jstagram.user.model.User;
import com.juhyung.jstagram.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    private static final String JSTAGRAM_USER_KEY = "USER";

    @Autowired
    private UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Login.class);
    }

    @Override
    public User resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Object userKey = webRequest.getAttribute(JSTAGRAM_USER_KEY, WebRequest.SCOPE_REQUEST);
        return userService.getUserByEmail((String) userKey);
    }
}
