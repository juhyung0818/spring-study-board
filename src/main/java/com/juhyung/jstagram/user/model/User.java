package com.juhyung.jstagram.user.model;

import com.juhyung.jstagram.common.builder.Builder;
import lombok.Data;

@Data
public class User {
    private final static User INSTANCE = Builder.of(User::new)
            .with(User::setId, "juhyung@naver.com")
            .with(User::setPassword, "123!@#")
            .with(User::setName, "yun ju hyung")
            .with(User::setNickName, "ju dang")
            .with(User::setUserKey, 1)
            .build();

    private int userKey;
    private String id;
    private String password;
    private String name;
    private String nickName;

    public static User get() {
        return INSTANCE;
    }
}
