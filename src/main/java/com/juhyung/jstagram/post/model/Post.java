package com.juhyung.jstagram.post.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Post {
    private int id;
    private String writer;
    private List<Integer> imageIds;
    @NotNull
    private String text;
    private LocalDateTime registerDate;
    private long likeCount;
    private boolean liked;
}
