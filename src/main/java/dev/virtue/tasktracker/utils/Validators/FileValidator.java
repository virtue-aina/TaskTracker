package dev.virtue.tasktracker.utils.Validators;

import dev.virtue.tasktracker.exception.StorageException;

public class FileValidator {

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
