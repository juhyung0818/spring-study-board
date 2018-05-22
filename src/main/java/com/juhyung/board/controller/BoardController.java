package com.juhyung.board.controller;

import com.juhyung.board.model.Board;
import com.juhyung.board.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.StringJoiner;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/java")
public class BoardController {
    private final BoardService boardService;

    @GetMapping
    public String mainPage(Model model) {
        model.addAttribute("boards", boardService.getBoards());
        return "/board/list";
    }

    @GetMapping("/api")
    @ResponseBody
    public List<Board> getBoards() {
        return boardService.getBoards();
    }

    @PostMapping
    @ResponseBody
    public void registerBoard(@RequestBody @Valid Board board, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new IllegalArgumentException("");
        }
        log.info(board.toString());
        if(board.getTitle() == null || board.getContent() == null) {
            throw new IllegalArgumentException("");
        }
        boardService.addBoard(board);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String illegalArgumentHandler() {
        return "등록 실패";
    }

}
