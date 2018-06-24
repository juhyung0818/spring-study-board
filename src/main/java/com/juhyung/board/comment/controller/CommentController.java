package com.juhyung.board.comment.controller;

import com.juhyung.board.comment.model.Comment;
import com.juhyung.board.comment.service.CommentService;
import com.juhyung.board.user.model.User;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
@RequestMapping("/java/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    @ResponseBody
    public Comment addComment(@RequestBody Comment comment) {
        if(comment == null){
            throw new IllegalArgumentException();
        }
        commentService.addComment(comment, User.get());
        return comment;
    }

}
