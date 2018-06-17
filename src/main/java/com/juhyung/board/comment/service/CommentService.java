package com.juhyung.board.comment.service;

import com.juhyung.board.comment.mapper.CommentMapper;
import com.juhyung.board.comment.model.Comment;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CommentService {
    private final CommentMapper commentMapper;

    public List<Comment> getCommentsByPostId(final int id) {
        return commentMapper.selectCommentsByPostId(id);
    }

    public void addComment(final Comment comment) {
        Optional.ofNullable(comment)
                .map(Comment::getContent)
                .orElseThrow(IllegalArgumentException::new);

        commentMapper.insert(comment);
    }
}
