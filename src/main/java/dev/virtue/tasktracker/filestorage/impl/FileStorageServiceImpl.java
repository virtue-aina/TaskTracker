package dev.virtue.tasktracker.filestorage.impl;

import dev.virtue.tasktracker.exception.StorageException;
import dev.virtue.tasktracker.filestorage.FileStorageService;
import dev.virtue.tasktracker.utils.Validators.FileValidator;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

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
     * Stores a file and returns the file name.
     * Validates the file properties and checks for valid file extensions.
     * @param file the file to store
     * @return the stored file name
     */
    @Override
    public String storeFile(MultipartFile file) {
        // validate file properties
        //TODO: GET ORIGINAL FILENAME
        String fileName = file.getOriginalFilename();//done
        //TODO: generate a unique identifier for the file
        String uniqueId = UUID.randomUUID().toString();//done
        //TODO:validate mime type
        String mimeType = file.getContentType();

        // get the file extension
        // use a utility method to check the file extension
        String fileExtension = FileValidator.extensionChecker(fileName);//done
        //TODO: create with the unique identifier and file extension
        String finalFileName = uniqueId + "." + fileExtension;
        //TODO:
        if (file.isEmpty()) {
            throw new StorageException("Cannot store empty file " + fileName );
        }

// Validate and normalize the file path
        //use "serverFileName" instead of original "finalFileName"
        String finalFileName = FileValidator.extensionChecker(fileName);
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
