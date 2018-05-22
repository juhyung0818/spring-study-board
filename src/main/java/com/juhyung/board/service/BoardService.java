package com.juhyung.board.service;

import com.juhyung.board.mapper.BoardMapper;
import com.juhyung.board.model.Board;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BoardService {
    private final BoardMapper boardMapper;

    public List<Board> getBoards() {
        return boardMapper.selectBoards();
    }

    public void addBoard(final Board board) {
        board.setViewCount(0);
        boardMapper.insertBoard(board);
    }
}