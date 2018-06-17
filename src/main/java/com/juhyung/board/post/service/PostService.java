package com.juhyung.board.post.service;


import com.juhyung.board.post.mapper.PostMapper;
import com.juhyung.board.post.model.Post;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class PostService {
    private final PostMapper boardMapper;

    public List<Post> getPosts() {
        return boardMapper.selectPosts();
    }

    public Post getPost(final int id) {
        boardMapper.updateViewCount(id);
        return boardMapper.selectPost(id);
    }

    public int registerPost(final Post post) {
        boardMapper.insert(post);
        return post.getId();
    }

    public void removePost(final int id) {
        boardMapper.delete(id);
    }

    public void modifyPost(final Post post) {
        if(post == null || StringUtils.isAnyBlank(post.getText(), post.getWriter())){
            log.error("Post : {}", post.toString());
            throw new IllegalArgumentException();
        }
        boardMapper.updatePost(post);
    }

}