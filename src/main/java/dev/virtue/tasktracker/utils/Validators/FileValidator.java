package dev.virtue.tasktracker.utils.Validators;

import dev.virtue.tasktracker.exception.StorageException;
import org.apache.tika.Tika;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Component
public class FileValidator {

    private final Tika tika;

    public FileValidator() {
        this.tika = new Tika();
    }

    /**
     * List of allowed media types for file uploads.
     * This list can be extended to include more types as needed.
     */
    private static final List<MediaType> ALL_ALLOWED_TYPES = Arrays.asList(
            MediaType.IMAGE_JPEG, MediaType.IMAGE_PNG, MediaType.IMAGE_GIF,
            MediaType.APPLICATION_PDF, MediaType.valueOf("application/msword"),
            MediaType.valueOf("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
            MediaType.valueOf("application/vnd.ms-excel"),
            MediaType.valueOf("application/vnd.ms-powerpoint")
    );
    public String validateExtension(String fileName) {

        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);

        //TODO: Check if the file extension ends with anything you expect
        if (fileName == null || fileName.isEmpty()) {
            throw new StorageException("File name cannot be empty");
        }

        if(fileExtension == null || fileExtension.endsWith(".exe") ||
                fileExtension.endsWith(".bat") || fileExtension.endsWith(".sh")) {
            throw new StorageException("Invalid file extension: " + fileExtension);
        } else{
            return  fileName + "." + fileExtension;
        }
    }

    public String validateContent(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new StorageException("File cannot be null or empty");
        }

        MediaType detectedMediaType;
        try(InputStream inputStream = file.getInputStream()) {
          String detectedTypeString = tika.detect(inputStream,file.getOriginalFilename());
            detectedMediaType = MediaType.parseMediaType(detectedTypeString);
        } catch (IOException e) {
            throw new StorageException("Could not determine file type", e);
        }





    }

}
