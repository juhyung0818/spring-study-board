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

    public Board getBoard(final int id) {
        return boardMapper.selectBoard(id);
    }

    public int registerBoard(final Board board) {
        board.setViewCount(0);
        boardMapper.insertBoard(board);
        return board.getId();
    }

    public void removeBoard(final int id) {
        boardMapper.deleteBoard(id);
    }
}