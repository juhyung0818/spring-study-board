package com.juhyung.board.comment.service;

import com.juhyung.board.comment.mapper.CommentMapper;
import com.juhyung.board.comment.model.Comment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CommentService {
    private final CommentMapper commentMapper;

    public List<Comment> getCommentsByPostId(final int id) {
        return commentMapper.selectCommentsByPostId(id);
    }

    public void addComment(final Comment comment) {
        commentMapper.insert(comment);
    }
}
