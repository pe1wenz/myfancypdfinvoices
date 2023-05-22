package com.marcobehler.myfancypdfinvoices.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;


// tag::adviceAnnotation[]
@RestControllerAdvice
public class GlobalExceptionHandler {
// end::adviceAnnotation[]

    // tag::handlemethodArgumentNotValid[]
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handlemethodArgumentNotValid(MethodArgumentNotValidException exception) { // <4>
        // TODO you can choose to return your custom object here, which will then get transformed to json/xml etc.
        return "Sorry, that was not quite right: @Valid Bean Validation error " + exception.getMessage();
    }
    // end::handlemethodArgumentNotValid[]

    // tag::handleConstraintViolation[]
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public String handleConstraintViolation(ConstraintViolationException exception) { // <4>
        // TODO you can choose to return your custom object here, which will then get transformed to json/xml etc.
        return "Sorry, that was not quite right: @RequestParams validation error " + exception.getMessage();
    }
    // end::handleConstraintViolation[]
}