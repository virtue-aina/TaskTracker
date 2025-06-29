package dev.virtue.tasktracker.utils.Validators;

import dev.virtue.tasktracker.exception.StorageException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class FileValidator {
    /**
     * List of allowed media types for file uploads.
     * This list can be extended to include more types as needed.
     */
    private static final List<MediaType> ALL_ALLOWED_TYPES = Arrays.asList(
            MediaType.IMAGE_JPEG, MediaType.IMAGE_PNG, MediaType.IMAGE_GIF,
            MediaType.APPLICATION_PDF, MediaType.valueOf("application/msword"),
            MediaType.valueOf("application/vnd.openxmlformats-officedocument.wordprocessingml.document")
    );




    public static String extensionChecker(String fileName) {

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


}
