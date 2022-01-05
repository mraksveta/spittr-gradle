package com.github.mraksveta.spittr.controller;

import com.github.mraksveta.spittr.model.SpittleImage;
import com.github.mraksveta.spittr.service.FileManagementService;
import com.github.mraksveta.spittr.service.SpittleImageService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/images")
public class ImageController {
    private final FileManagementService fileManagementService;
    private final SpittleImageService spittleImageService;

    public ImageController(FileManagementService fileManagementService, SpittleImageService spittleImageService) {
        this.fileManagementService = fileManagementService;
        this.spittleImageService = spittleImageService;
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<byte[]> getImage(@PathVariable("fileName") String fileName) {
        SpittleImage spittleImage = spittleImageService.findByFileName(fileName);
        byte[] content = fileManagementService.getContent(spittleImage.getFileName());
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(spittleImage.getContentType().getContentType()))
                .body(content);
    }
}
