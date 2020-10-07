package com.example.sos.api.exceptions;

public class FileStorageException extends RuntimeException {

    private static final long serialVersionUID = -5121351905183532653L;

    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
