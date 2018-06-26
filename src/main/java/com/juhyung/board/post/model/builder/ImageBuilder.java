package com.juhyung.board.post.model.builder;

import com.juhyung.board.post.model.Image;

public final class ImageBuilder {
    private int id;
    private int postId;
    private String fileName;
    private String savedFileName;
    private long fileSize;
    private String contentType;
    private int deleted;

    private ImageBuilder() {
    }

    public static ImageBuilder builder() {
        return new ImageBuilder();
    }

    public ImageBuilder postId(int postId) {
        this.postId = postId;
        return this;
    }

    public ImageBuilder fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public ImageBuilder savedFileName(String savedFileName) {
        this.savedFileName = savedFileName;
        return this;
    }

    public ImageBuilder fileSize(long fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    public ImageBuilder contentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public Image build() {
        Image image = new Image();
        image.setPostId(postId);
        image.setFileName(fileName);
        image.setSavedFileName(savedFileName);
        image.setFileSize(fileSize);
        image.setContentType(contentType);
        return image;
    }
}