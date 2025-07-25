package dev.virtue.tasktracker.utils.Validators;

import dev.virtue.tasktracker.exception.StorageException;
import org.apache.tika.Tika;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * FileValidator is a utility class that validates file extensions and content types.
 * It uses Apache Tika to detect the media type of files and checks against allowed types.
 */

@Component
public class FileValidator {
    private final Tika tika;

    public FileValidator() {
        this.tika = new Tika();
    }
    private static final List<MediaType> ALL_ALLOWED_TYPES = Arrays.asList(
            MediaType.IMAGE_JPEG, MediaType.IMAGE_PNG, MediaType.IMAGE_GIF,
            MediaType.APPLICATION_PDF, MediaType.valueOf("application/msword"),
            MediaType.valueOf("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
            MediaType.valueOf("application/vnd.ms-excel"),
            MediaType.valueOf("application/vnd.ms-powerpoint")
    );
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png", "gif", "pdf", "doc", "docx", "xls", "xlsx", "ppt", "pptx");

    public String validateExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            throw new StorageException("File name cannot be empty");
        }
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);

        if(!ALLOWED_EXTENSIONS.contains(fileExtension)) {
            throw new StorageException("Invalid file extension: " + fileExtension);
        } else{
            return fileExtension;
        }
    }

    public MediaType validateContent(MultipartFile file) {

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

        if (!ALL_ALLOWED_TYPES.contains(detectedMediaType)) {
            throw new StorageException("Unsupported file type: " + detectedMediaType);
        }else {
            return detectedMediaType;
        }

    }

}
