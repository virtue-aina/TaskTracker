package dev.virtue.tasktracker.filestorage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.util.Optional;

public interface FileStorageService {

    void init();
    String storeFile(MultipartFile file);
    Optional<Resource> loadFileAsResource(String fileName);
    void delete(String filename);
}
