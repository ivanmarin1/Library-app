package de.bdr.springiodemo.controller;

import de.bdr.springiodemo.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

//TODO fix this controller
@ControllerAdvice
public class ControllerExceptionHandler {

    //TODO add a generic exception on the end
    //TODO add exceptions for incomplete body data in http request

    @ExceptionHandler(value = {
            UserAlreadyExistsException.class,
            UserNotFoundException.class,
            BookAlreadyExistsException.class,
            BookNotFoundException.class,
            AuthorAlreadyExistsException.class,
            AuthorNotFoundException.class})
    protected ResponseEntity<Object> handleException(UserAlreadyExistsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
