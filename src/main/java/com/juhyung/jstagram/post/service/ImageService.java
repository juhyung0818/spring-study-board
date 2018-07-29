package com.juhyung.jstagram.post.service;

import com.juhyung.jstagram.common.builder.Builder;
import com.juhyung.jstagram.post.mapper.ImageMapper;
import com.juhyung.jstagram.post.model.Image;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageMapper imageMapper;

    @Value("${spring.jastgram.file-path}")
    private String BASE_DIR;

    private void save(final int postId, final MultipartFile[] files) {
        String path = getPathOrMakeDirectory();

        Image image;
        for (MultipartFile file : files) {
            image = convertFile2Image(file, path);

            try (InputStream in = file.getInputStream();
                 FileOutputStream out = new FileOutputStream(image.getSavedFileName())) {
                int readCount;
                byte[] buffer = new byte[512];
                while ((readCount = in.read(buffer)) != -1) {
                    out.write(buffer, 0, readCount);
                }
            } catch (Exception e) {
                log.error("image : {}, exception : {}", image, e);
            }
            image.setPostId(postId);
            imageMapper.insertImage(image);
        }
    }

    public void upload(final int postId, final MultipartFile[] files) {
        Optional.ofNullable(files)
                .filter(file -> file.length > 0)
                .ifPresent((images) -> save(postId, images));
    }

    private String getPathOrMakeDirectory() {
        String path = getPath();
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        return path;
    }

    private String getPath() {
        return new StringJoiner(File.separator)
                .add(BASE_DIR)
                .add(String.valueOf(LocalDate.now().getYear()))
                .add(String.valueOf(LocalDate.now().getMonthValue()))
                .add(String.valueOf(LocalDate.now().getDayOfMonth()))
                .toString();
    }

    private Image convertFile2Image(MultipartFile file, String path) {
        return Builder.of(Image::new)
                .with(Image::setContentType, file.getContentType())
                .with(Image::setFileName, file.getName())
                .with(Image::setFileSize, file.getSize())
                .with(Image::setSavedFileName, file.getOriginalFilename())
                .build();
    }

    private String getSaveFileName(String path) {
        return new StringJoiner(File.separator)
                .add(path)
                .add(UUID.randomUUID().toString())
                .toString();
    }

    public void getImage(HttpServletResponse response, final int imageId) {
        Image image = imageMapper.selectImage(imageId);

        File readFile = new File(image.getSavedFileName());
        if (!readFile.exists()) { // 파일이 존재하지 않다면
            throw new RuntimeException("file not found");
        }

        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "inline" + "; filename=" + image.getSavedFileName() + ";");
        response.setHeader(HttpHeaders.TRANSFER_ENCODING, "binary");
        response.setHeader(HttpHeaders.CONTENT_TYPE, image.getContentType());
        response.setHeader(HttpHeaders.CONTENT_LENGTH, "" + image.getFileSize());

        try (FileInputStream fis = new FileInputStream(readFile)) {
            FileCopyUtils.copy(fis, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> getImageIds(final int postId) {
        return imageMapper.selectImageIds(postId);
    }

}