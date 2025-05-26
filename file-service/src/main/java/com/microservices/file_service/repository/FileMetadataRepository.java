package com.microservices.file_service.repository;
import com.microservices.file_service.model.FileMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileMetadataRepository extends JpaRepository<FileMetadata, Long> {
}

