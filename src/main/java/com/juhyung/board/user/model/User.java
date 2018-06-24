package com.juhyung.board.user.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private final static User INSTANCE = User.builder()
            .key(1)
            .id("juhyung@naver.com")
            .password("123!@#")
            .name("juhyung.yun")
            .nickName("jh")
            .build();

    private int key;
    private String id;
    private String password;
    private String name;
    private String nickName;

    public static User get() {
        return INSTANCE;
    }
}