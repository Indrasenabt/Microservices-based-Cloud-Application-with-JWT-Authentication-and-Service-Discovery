package com.microservices.file_service.model;

import jakarta.persistence.*;

@Entity
@Table(name="file_table")
public class FileMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;
    private String fileType;
    private Long size;

    // Add other metadata fields if needed

    public FileMetadata() {
    }

    public FileMetadata(Long id, String filename, String fileType, Long size) {
        this.id = id;
        this.filename = filename;
        this.fileType = fileType;
        this.size = size;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFilename() { return filename; }
    public void setFilename(String filename) { this.filename = filename; }

    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }

    public Long getSize() { return size; }
    public void setSize(Long size) { this.size = size; }
}

