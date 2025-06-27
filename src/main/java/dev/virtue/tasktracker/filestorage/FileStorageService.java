package dev.virtue.tasktracker.filestorage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.util.Optional;

public interface FileStorageService {

    void init();
    /**
     * Stores a file and returns the file name.
     * @param file     the file to store
     * @param fileName the name to save the file as
     * @return the stored file name
     */
    String storeFile(MultipartFile file, String fileName);

    /**
     * Loads a file as a resource.
     * @param fileName the name of the file to load
     * @return an Optional containing the resource if found, or empty if not found
     */
    Optional<Resource> loadFileAsResource(String fileName);
    /**
     * Deletes a file by its name.
     * @param filename the name of the file to delete
     */
    void delete(String filename);


}
