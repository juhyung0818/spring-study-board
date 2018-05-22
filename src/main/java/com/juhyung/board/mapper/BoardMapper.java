package com.juhyung.board.mapper;

import com.juhyung.board.model.Board;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BoardMapper {
    @Select("SELECT * FROM board")
    List<Board> selectBoards();

    @Insert({"INSERT INTO board (title, content, view_count) " +
            "VALUES (#{title}, #{content}, #{viewCount})"})
    void insertBoard(final Board board);
}
