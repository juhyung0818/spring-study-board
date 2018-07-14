
package com.juhyung.jstagram.user.login.service;

import java.util.Optional;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.juhyung.jstagram.user.mapper.UserMapper;
import com.juhyung.jstagram.user.model.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Slf4j
public class LoginService {
    private static final Pattern EMAIL_REGULAR_EXPRESSION = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    private final UserMapper userMapper;

    public User login(final String id, final String password) {
        if (!validLoginInput(id, password)) {
            throw new IllegalArgumentException("Invalid Input : [id : " + id + ", password : " + password + "]");
        }

        return Optional.ofNullable(userMapper.selectLoginUser(id, password))
                .orElseThrow(() -> new IllegalStateException("Login Failed!! User : [id : " + id + ", password : " + password + "]"));
    }

    private boolean validLoginInput(final String id, final String password) {
        return validId(id) && password != null;
    }

    private boolean validId(final String id) {
        return EMAIL_REGULAR_EXPRESSION.matcher(id).matches();
    }

}