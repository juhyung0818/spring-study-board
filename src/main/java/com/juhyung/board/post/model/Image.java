package com.juhyung.board.post.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Image {
    private int id;
    private int userId;
    private String fileName;
    private String saveFileName;
    private long fileSize;
    private String contentType;
    private int deleteFlag;
    private LocalDateTime registerDate;
}
