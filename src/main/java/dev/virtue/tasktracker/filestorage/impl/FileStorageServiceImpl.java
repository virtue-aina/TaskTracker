package dev.virtue.tasktracker.filestorage.impl;

import dev.virtue.tasktracker.exception.StorageException;
import dev.virtue.tasktracker.filestorage.FileStorageService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
@Slf4j
public class FileStorageServiceImpl implements FileStorageService {
    /**
     * The location where files will be stored.
     * This can be configured via application properties.
     */
    @Value("${app.storage.location:uploads}")
    private String storageLocation;
    private Path rootLocation;
    /**
     * Initializes the storage directory.
     * Creates the directory if it does not exist.
     */
    @PostConstruct
    @Override
    public void init(){
        rootLocation = Paths.get(storageLocation);
        try{
            Files.createDirectories(rootLocation);
        } catch (IOException e){
            throw new StorageException("Could not create storage directory", e);
        }
    }
    /**
     * Stores a file with the given name.
     * Validates the file properties and ensures it is not empty.
     * Validates the content type of the file.
     * Normalizes the file path to prevent directory traversal attacks.
     * Ensures the file is stored within the root directory.
     * Checks if the file already exists and replaces it if necessary.
     * @param file     The MultipartFile to store
     * @param fileName The name to store the file as
     * @return The final file name after storage
     */
    @Override
    public String storeFile(MultipartFile file, String fileName) {
        // validate file properties
        if (file.isEmpty()) {
            throw new StorageException("Cannot store empty file " + fileName);
        }
        String fileExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String finalFileName = getString(file, fileName, fileExtension);

// Validate and normalize the file path
        Path targetLocation = rootLocation
                .resolve(Paths.get(finalFileName))
                .normalize()
                .toAbsolutePath();
// Ensure the target location is within the root directory
        if (!targetLocation.getParent().equals(rootLocation.toAbsolutePath())) {
            throw new StorageException("Cannot store file outside the current directory");
        }
// Check if the file already exists then store the file
        try(InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return finalFileName;
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + finalFileName, e);
        }
    }

    private static String getString(MultipartFile file, String fileName, String fileExtension) {
        String finalFileName = fileName + "." + fileExtension;

        String contentType = file.getContentType();
        // Validate the content type of the file
        if (contentType == null ||
                (!contentType.equals("image/jpeg") &&
                        !contentType.equals("image/png") &&
                        !contentType.equals("application/pdf") &&
                        !contentType.equals("text/plain") &&
                        !contentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document") && // .docx
                        !contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") && // .xlsx
                        !contentType.equals("application/vnd.openxmlformats-officedocument.presentationml.presentation") && // .pptx
                        !contentType.equals("image/gif")//

                )
        ) {
            throw new StorageException("Invalid file type: " + contentType + " for " + finalFileName);
        }
        return finalFileName;
    }

    /**
     * Loads a file as a Resource.
     * Resolves the file path relative to the root location and checks if it exists and is readable.
     * @param fileName The name of the file to load
     * @return An Optional containing the Resource if found, or empty if not found
     */
    @Override
    public Optional<Resource> loadFileAsResource(String fileName) {
        try {
            // Resolve the file path relative to our root location
            Path file = rootLocation.resolve(fileName);
            // Create a Resource object from the file path
            Resource resource = new UrlResource(file.toUri());
            // Check if the resource exists and is readable
            if (resource.exists() || resource.isReadable()) {
                return Optional.of(resource);
            } else {
                return Optional.empty();
            }
        } catch (MalformedURLException e) {
            log.debug("Could not read file: " + fileName, e);
            return Optional.empty();
        }
    }

    @Override
    public void delete(String filename) {

    }
}
