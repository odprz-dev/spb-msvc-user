package com.msvc.usuario.exceptions.handlers;

import com.msvc.usuario.exceptions.BadRequestException;
import com.msvc.usuario.exceptions.ResourceNotFoundException;
import com.msvc.usuario.responses.ApiExceptionResponse;
import com.msvc.usuario.utils.ApiExceptionBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiExceptionResponse> handlerResourceNotFountException(ResourceNotFoundException rnfException) {

        String errorCode = rnfException.getErrorCode() != null ? rnfException.getErrorCode() : "GC-001";

        ApiExceptionResponse apiExceptionResponse = ApiExceptionBuilder.build(
                HttpStatus.NOT_FOUND,
                rnfException.getMessage(),
                errorCode,
                true,
                rnfException.getTimeStamp());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiExceptionResponse);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiExceptionResponse> handlerBadRequestException(BadRequestException brException) {

        String errorCode = brException.getErrorCode() != null ? brException.getErrorCode() : "GC-002";

        ApiExceptionResponse apiExceptionResponse = ApiExceptionBuilder.build(
                HttpStatus.BAD_REQUEST,
                brException.getMessage(),
                errorCode,
                false,
                brException.getTimeStamp());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiExceptionResponse);

    }
}
