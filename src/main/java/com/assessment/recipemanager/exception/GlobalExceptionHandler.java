package com.assessment.recipemanager.exception;

import com.assessment.recipemanager.model.ResponseError;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.HandlerMethod;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ResponseError> processValidationIllegalError(HttpMessageNotReadableException ex,
                                                                HandlerMethod handlerMethod, WebRequest webRequest) {

        ResponseError error = new ResponseError(ex.getMostSpecificCause().getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<ResponseError> entityNotFound(EntityNotFoundException ex){
        ResponseError error = new ResponseError("Could not find the entity with the given Id");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<ResponseError> entityNotFound(EmptyResultDataAccessException ex){
        ResponseError error = new ResponseError("Could not find the entity with the given Id");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
