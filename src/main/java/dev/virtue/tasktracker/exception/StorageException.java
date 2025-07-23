package dev.virtue.tasktracker.exception;

public class StorageException extends BaseException{
    public StorageException() {
        super();
    }
    public StorageException(String message) {
        super(message);
    }
    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
