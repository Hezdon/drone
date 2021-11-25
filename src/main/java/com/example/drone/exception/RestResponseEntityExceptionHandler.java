package com.example.drone.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        //to extract the default error message from a diagnostic
        // information about the errors held in MethodArgumentNotValidException
        Exception exception = new Exception(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return this.createResponseEntity(HttpStatus.BAD_REQUEST, exception, request);
    }

    private ResponseEntity<Object> createResponseEntity(
            HttpStatus httpStatus, Exception ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status("")
                .responseMessage(ex.getMessage())
                .responseCode("")
                .build();
        return handleExceptionInternal(ex, errorResponse,
                new HttpHeaders(), httpStatus, request);
    }

}
