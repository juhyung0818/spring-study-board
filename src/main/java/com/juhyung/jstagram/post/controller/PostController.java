package com.juhyung.jstagram.post.controller;

import com.juhyung.jstagram.comment.service.CommentService;
import com.juhyung.jstagram.common.annotation.Login;
import com.juhyung.jstagram.post.model.Post;
import com.juhyung.jstagram.post.service.PostService;
import com.juhyung.jstagram.user.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/java/posts")
public class PostController {
    private final PostService postService;
    private final CommentService commentService;

    @GetMapping
    public String getPostsPage(Model model, @Login User user) {
        model.addAttribute("posts", postService.getPosts(user));
        return "/post/list";
    }

    @GetMapping("/{id}")
    public String getBoard(Model model, @PathVariable int id) {
        if(id <= 0) {
            return "forward:/java/posts";
        }
        model.addAttribute("post", postService.getPost(id));
        model.addAttribute("comments", commentService.getComments(id));
        return "/post/post";
    }

    @GetMapping("/new")
    public String getNewPost() {
        return "/post/new";
    }

    @PostMapping
    public String registerBoard(@Login User user, @Valid Post post, MultipartFile[] multipartFiles) {
        post.setWriter(user.getNickName());
        int id = postService.registerPost(post, multipartFiles);
        return "redirect:/java/posts/" + id;
    }

    @PostMapping("/modify")
    public String modifyBoard(Post post) {
        log.info(post.toString());
        postService.modifyPost(post);
        return "redirect:/java/posts/" + post.getId();
    }

    @DeleteMapping("/{id}")
    public String removeBoard(@PathVariable int id) {
        if(0 < id) {
            postService.removePost(id);
        }
        return "redirect:/java/posts";
    }

    @PutMapping("/{id}/like")
    @ResponseBody
    public void modifyLike(@PathVariable int id, @Login User user) {
        postService.modifyLikeCount(id, user);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String illegalArgumentHandler(IllegalArgumentException iae) {
        log.error(iae.getMessage(), iae);
        return "fail";
    }

}
