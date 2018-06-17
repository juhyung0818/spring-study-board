package com.juhyung.board.post.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;

@Controller
@RequestMapping
public class FileController {
    private static final String FILE_PATH = "/Users/YJH/Desktop/tracer.png";
    private static final String INLINE = "inline";
    private static final String ATTACHMENT = "attachment";
    private static final int FILE_SIZE = 358_939;

    @GetMapping
    public void getImage(HttpServletResponse response) {
        /* attachment 또는 inline 사용 */
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, INLINE + "; filename=\"tracer.png\";");
        response.setHeader(HttpHeaders.TRANSFER_ENCODING, "binary");
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_PNG_VALUE);
        response.setHeader(HttpHeaders.CONTENT_LENGTH, "" + FILE_SIZE);

        File readFile = new File(FILE_PATH);
        if (!readFile.exists()) { // 파일이 존재하지 않다면
            throw new RuntimeException("file not found");
        }

        try(FileInputStream fis = new FileInputStream(readFile)) {
            FileCopyUtils.copy(fis, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    @GetMapping("/get")
    @ResponseBody
    public String get() {
        return "get";
    }

    @PostMapping("/post")

    public String post() {
        return "redirect:/get";
    }
}
