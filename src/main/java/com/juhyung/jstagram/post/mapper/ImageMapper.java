package com.juhyung.jstagram.post.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.juhyung.jstagram.post.model.Image;

import java.util.List;

@Mapper
public interface ImageMapper {
    @Insert({"INSERT INTO image "
            + "( "
            + "  post_id "
            + ", file_name "
            + ", saved_file_name "
            + ", file_size "
            + ", content_type "
            + ", deleted "
            + ") "
            + "VALUES "
            + "( "
            + "  #{postId} "
            + ", #{fileName} "
            + ", #{savedFileName} "
            + ", #{fileSize} "
            + ", #{contentType} "
            + ", #{deleted} "
            + ")"})
    void insertImage(final Image image);

    @Select({"SELECT "
            + "id"
            + ", post_id AS postId "
            + ", file_name AS fileName"
            + ", saved_file_name As savedFileName "
            + ", file_size AS fileSize "
            + ", content_type AS contentType"
            + ", deleted AS deleted "
            + "FROM image "
            + "WHERE id = #{imageId}"})
    Image selectImage(final int imageId);

    @Select({"SELECT id " +
            "FROM image " +
            "WHERE post_id = #{postId}"})
    List<Integer> selectImageIds(final int postId);
}