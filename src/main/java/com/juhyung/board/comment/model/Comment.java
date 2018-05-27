package com.juhyung.board.comment.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    private int postId;
    private int id;
    private String content;
    private LocalDateTime registerDate;
}
