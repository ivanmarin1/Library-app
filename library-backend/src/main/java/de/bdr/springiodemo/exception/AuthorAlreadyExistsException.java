package de.bdr.springiodemo.exception;

public class AuthorAlreadyExistsException extends Exception {

    public AuthorAlreadyExistsException() {
        super();
    }

    public AuthorAlreadyExistsException(String message) {
        super(message);
    }
}
