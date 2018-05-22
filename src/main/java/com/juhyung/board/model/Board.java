package com.juhyung.board.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Board {
    private int id;
    @NotNull
    private String title;
    @NotNull
    private String content;
    private long viewCount;
}
