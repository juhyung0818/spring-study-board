package com.juhyung.board.post.service;


import com.juhyung.board.post.mapper.PostMapper;
import com.juhyung.board.post.model.Post;
import com.juhyung.board.user.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class PostService {
    private final PostMapper postMapper;

    public List<Post> getPosts(User user) {
        return postMapper.selectPosts(user.getKey());
    }

    public Post getPost(final int id) {
        return postMapper.selectPost(id);
    }

    public int registerPost(final Post post) {
        postMapper.insert(post);
        return post.getId();
    }

    public void removePost(final int id) {
        postMapper.delete(id);
    }

    public void modifyPost(final Post post) {
        if(post == null || StringUtils.isAnyBlank(post.getText(), post.getWriter())){
            log.error("Post : {}", post.toString());
            throw new IllegalArgumentException();
        }
        postMapper.updatePost(post);
    }

    public void modifyLikeCount(final int id, final User user) {
        final int key = user.getKey();
        boolean liked = postMapper.deleteUserLikePost(id, key) == 0;

        if(liked) {
            postMapper.updateLikeCount(id);
            postMapper.insertUserLikePost(id, key);
            return ;
        }
        postMapper.updateUnLikeCount(id);
    }

}