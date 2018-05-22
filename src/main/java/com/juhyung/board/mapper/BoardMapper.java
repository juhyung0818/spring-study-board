package com.juhyung.board.mapper;

import com.juhyung.board.model.Board;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface BoardMapper {
    @Select("SELECT * FROM board")
    List<Board> selectBoards();

    @Select({"SELECT * FROM board WHERE id = #{id}"})
    Board selectBoard(final int id);

    @Insert({"INSERT INTO board (title, content, register_date, view_count) " +
            "VALUES (#{title}, #{content}, NOW(), #{viewCount})"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertBoard(final Board board);

    @Delete({"DLELETE FROM board WHERE id = #{id}"})
    void deleteBoard(final int id);
}
