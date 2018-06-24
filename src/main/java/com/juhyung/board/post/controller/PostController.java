package com.juhyung.board.post.controller;

import com.juhyung.board.comment.service.CommentService;
import com.juhyung.board.post.model.Post;
import com.juhyung.board.post.service.PostService;
import com.juhyung.board.user.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/java/posts")
public class PostController {
    private final PostService postService;
    private final CommentService commentService;

    @GetMapping
    public String mainPage(Model model) {
        model.addAttribute("posts", postService.getPosts(User.get()));
        return "/post/list";
    }

    @PostMapping("/test")
    public void test(@RequestBody String msg) {
        System.out.println(msg);
    }

    @GetMapping("/api")
    @ResponseBody
    public List<Post> getBoards() {
        return postService.getPosts(User.get());
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
    public String registerBoard(@Valid Post post, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.error(post.toString());
            throw new IllegalArgumentException("");
        }
        int id = postService.registerPost(post);
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
    public void modifyLike(@PathVariable int id) {
        postService.modifyLikeCount(id, User.get());
    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String illegalArgumentHandler(IllegalArgumentException iae) {
        log.error(iae.getMessage(), iae);
        return "fail";
    }

}
