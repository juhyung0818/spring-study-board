package com.juhyung.jstagram.post.service;


import com.juhyung.jstagram.post.mapper.PostMapper;
import com.juhyung.jstagram.post.model.Post;
import com.juhyung.jstagram.user.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class PostService {
    private final PostMapper postMapper;
    private final ImageService imageService;

    public List<Post> getPosts(User user) {
        List<Post> posts = postMapper.selectPosts(user.getUserKey());
        posts.forEach(post -> post.setImageIds(imageService.getImageIds(post.getId())));
        return posts;
    }

    public List<Post> getPosts(final int userKey) {
        return postMapper.selectUserPosts(userKey);
    }

    public Post getPost(final int id) {
        Post post = postMapper.selectPost(id);
        post.setImageIds(imageService.getImageIds(id));
        return post;
    }

    @Transactional
    public int registerPost(final Post post, final MultipartFile[] multipartFiles) {
        postMapper.insert(post);

        int id = post.getId();
        imageService.upload(id, multipartFiles);
        return id;
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
        final int key = user.getUserKey();
        boolean liked = postMapper.deleteUserLikePost(id, key) == 0;

        if(liked) {
            postMapper.updateLikeCount(id);
            postMapper.insertUserLikePost(id, key);
            return ;
        }
        postMapper.updateUnLikeCount(id);
    }

}