package de.bdr.springiodemo.exception;

public class AuthorNotFoundException extends Exception {

    public AuthorNotFoundException() {
        super();
    }

    public AuthorNotFoundException(String message) {
        super(message);
    }
}
