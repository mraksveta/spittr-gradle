package com.github.mraksveta.spittr.service.impl;

import com.github.mraksveta.spittr.exception.FileReadingException;
import com.github.mraksveta.spittr.exception.FileWritingException;
import com.github.mraksveta.spittr.exception.UnsupportedContentTypeException;
import com.github.mraksveta.spittr.service.FileManagementService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileManagementServiceImpl implements FileManagementService {
    private final String mediaRoot;

    public FileManagementServiceImpl(@Value("${spittr.media.root}") String mediaRoot) {
        this.mediaRoot = mediaRoot;
    }

    @Override
    public String uploadFile(MultipartFile file) {
        String uuid = UUID.randomUUID().toString();
        String contentType = file.getContentType();
        String extension = getExtensionForContentType(contentType);
        if (extension == null) {
            throw new UnsupportedContentTypeException("Content type \"" + contentType + "\" " +
                    "aren't supported or is not specified");
        }
        String fileName = uuid + "." + extension;

        try {
            Path target = Paths.get(mediaRoot, fileName);
            if (Files.exists(target)) throw new FileAlreadyExistsException(target.toString());
            file.transferTo(target);
        } catch (IOException e) {
            throw new FileWritingException("Failure while writing file \"" + uuid + "." + extension +
                    "\" in mediaRoot \"" +
                    mediaRoot + "\"");
        }

        return fileName;
    }

    @Override
    public byte[] getContent(String fileName) {
        Path source = Paths.get(mediaRoot, fileName);
        try {
            return Files.readAllBytes(source);
        } catch (IOException e) {
            throw new FileReadingException("Failure while reading file \"" + fileName + "\" in mediaRoot \"" +
                    mediaRoot + "\"");
        }
    }

    private String getExtensionForContentType(String contentType) {
        if (contentType == null) return null;
        switch (contentType) {
            case MediaType.IMAGE_PNG_VALUE:
                return "png";
            case MediaType.IMAGE_JPEG_VALUE:
                return "jpg";
            case MediaType.IMAGE_GIF_VALUE:
                return "gif";
            default:
                return null;
        }
    }
}
