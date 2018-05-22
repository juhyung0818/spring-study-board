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

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/java/boards")
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

    @GetMapping("/{id}")
    public String getBoard(Model model, @PathVariable int id) {
        if(id <= 0) {
            return "forward:/java/boards";
        }
        model.addAttribute("board", boardService.getBoard(id));
        return "/board/board";
    }

    @GetMapping("/new")
    public String getNewPost() {
        return "/board/post";
    }

    @PostMapping
    public String registerBoard(@Valid Board board, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.error(board.toString());
            throw new IllegalArgumentException("");
        }
        int id = boardService.registerBoard(board);
        return "redirect:/java/boards/" + id;
    }

    @DeleteMapping("/{id}")
    public String removeBoard(@PathVariable int id) {
        if(0 < id) {
            boardService.removeBoard(id);
        }
        return "redirect:/java/boards";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String illegalArgumentHandler(IllegalArgumentException iae) {
        log.error(iae.getMessage(), iae);
        return "등록 실패";
    }

}
