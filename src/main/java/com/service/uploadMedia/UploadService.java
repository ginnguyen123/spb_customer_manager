package com.service.uploadMedia;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface UploadService {
    Map uploadImage(MultipartFile multipartFile, Map options) throws IOException;

    Map destroyImage(String publicId, Map options) throws IOException;

    Map uploadVideo(MultipartFile multipartFile, Map options) throws IOException;

    Map destroyVideo(String publicId, Map options) throws IOException;
}
