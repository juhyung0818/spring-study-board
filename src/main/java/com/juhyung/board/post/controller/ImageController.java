package com.juhyung.board.post.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.juhyung.board.post.service.ImageService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/java/images")
public class ImageController {
    private final ImageService imageService;

    @GetMapping("/{id}")
    public void getImage(HttpServletResponse response, @PathVariable int id) {
        imageService.getImage(response, id);
    }

    @PostMapping
    public String upload(int postId, MultipartFile[] multipartFiles) {
        imageService.upload(postId, multipartFiles);
        return "redirect:/java/posts/" + postId;
    }
}