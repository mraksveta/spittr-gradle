package com.github.mraksveta.spittr.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileManagementService {
    String uploadFile(MultipartFile file);

    byte[] getContent(String fileName);
}
