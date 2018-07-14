package com.juhyung.jstagram.post.model;

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

    public void setId(Image image, int id) {
        image.setId(id);
    }
}