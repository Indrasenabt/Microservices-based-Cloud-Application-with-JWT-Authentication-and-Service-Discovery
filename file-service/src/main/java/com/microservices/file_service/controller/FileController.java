package com.microservices.file_service.controller;
import com.microservices.file_service.model.FileMetadata;
import com.microservices.file_service.repository.FileMetadataRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileController {

    private final FileMetadataRepository fileMetadataRepository;

    public FileController(FileMetadataRepository fileMetadataRepository) {
        this.fileMetadataRepository = fileMetadataRepository;
    }

    // For simplicity, store file metadata only. For actual files, use file system or cloud storage.
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        FileMetadata metadata = new FileMetadata();
        metadata.setFilename(file.getOriginalFilename());
        metadata.setFileType(file.getContentType());
        metadata.setSize(file.getSize());

        fileMetadataRepository.save(metadata);

        return new ResponseEntity<>("File metadata saved successfully", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileMetadata> getFileMetadata(@PathVariable Long id) {
        return fileMetadataRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

