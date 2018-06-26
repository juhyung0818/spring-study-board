package com.juhyung.board.post.model;

import lombok.Data;

@Data
public class Image {
    private int id;
    private int postId;
    private String fileName;
    private String savedFileName;
    private long fileSize;
    private String contentType;
    private int deleted;

}