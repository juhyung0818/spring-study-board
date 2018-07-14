package com.juhyung.jstagram.comment.service;

import com.juhyung.jstagram.comment.mapper.CommentMapper;
import com.juhyung.jstagram.comment.model.Comment;
import com.juhyung.jstagram.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CommentService {
    private final CommentMapper commentMapper;

    public List<Comment> getComments(final int id) {
        List<Comment> comments = commentMapper.selectComments(id);
        List<Comment> childComments = commentMapper.selectChildComments(id);

        comments.stream()
                .forEach(
                         p -> p.setComments(
                                 childComments.stream()
                                            .filter(c -> p.getId() == c.getParent())
                                            .collect(Collectors.toList())
                         )
                );


        comments.forEach((comment) -> comment.setUserName("juhyung"));
        return comments;
    }

    public void addComment(final Comment comment, final User user) {
        Optional.ofNullable(comment)
                .map(Comment::getContent)
                .orElseThrow(IllegalArgumentException::new);

        comment.setUserKey(user.getUserKey());
        commentMapper.insert(comment);
    }
}
