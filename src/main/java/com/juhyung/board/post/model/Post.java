package com.juhyung.board.post.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Post {
    private int id;
    private String writer;
    private List<Image> image;
    @NotNull
    private String text;
    private LocalDateTime registerDate;
    private long likeCount;
    private boolean liked;
}
