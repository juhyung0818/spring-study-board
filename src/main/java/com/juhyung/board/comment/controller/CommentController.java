package com.juhyung.board.comment.controller;

import com.juhyung.board.comment.model.Comment;
import com.juhyung.board.comment.service.CommentService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/java/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public void addComment(Comment comment) {
        if(comment == null || StringUtils.isBlank(comment.getContent())){
            throw new IllegalArgumentException();
        }
        commentService.addComment(comment);
    }

}
