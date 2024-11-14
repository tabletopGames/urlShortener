package com.piotr.wanat.urlshortener.validation;

import com.piotr.wanat.urlshortener.validation.dto.ErrorResponse;
import com.piotr.wanat.urlshortener.validation.exceptions.NotFoundShortUrlException;
import com.piotr.wanat.urlshortener.validation.exceptions.UniqueUrlException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
public class ValidationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ErrorResponse> list = ex.getAllErrors().stream().map(error -> new ErrorResponse(HttpStatus.BAD_REQUEST.name(), error.getDefaultMessage())).toList();
        return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UniqueUrlException.class)
    public ResponseEntity<Object> handleUniqueUrlException(UniqueUrlException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.name(), ex.getMessage());
        return new ResponseEntity<>(List.of(errorResponse), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundShortUrlException.class)
    public ResponseEntity<Object> handleCustomServiceException(NotFoundShortUrlException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.name(), ex.getMessage());
        return new ResponseEntity<>(List.of(errorResponse), HttpStatus.NOT_FOUND);
    }
}
