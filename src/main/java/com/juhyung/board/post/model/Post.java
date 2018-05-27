package com.juhyung.board.post.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class Post {
    private int id;
    @NotNull
    private String title;
    @NotNull
    private String content;
    private LocalDateTime registerDate;
    private long viewCount;
}
