package com.juhyung.board.comment.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Comment {
    private int id;
    private int postId;
    private int userKey;
    private String userName;
    private String content;
    private int parent;
    private LocalDateTime registerDate;

    private List<Comment> comments;
}