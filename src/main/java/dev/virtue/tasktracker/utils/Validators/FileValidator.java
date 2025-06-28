package dev.virtue.tasktracker.utils.Validators;

import dev.virtue.tasktracker.exception.StorageException;

public class FileValidator {

    private static String getString(String fileName, String fileExtension) {
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
