package com.msvc.usuario.utils;

import com.msvc.usuario.responses.ApiExceptionResponse;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public final class ApiExceptionBuilder {

    private ApiExceptionBuilder() {}

    public static ApiExceptionResponse build(HttpStatus statusCode,
                                             String message,
                                             String errorCode,
                                             boolean success,
                                             LocalDateTime timeStamp) {

        ApiExceptionResponse exceptionResponse = new ApiExceptionResponse();

        exceptionResponse.setMsg(message);
        exceptionResponse.setErrorCode(errorCode);
        exceptionResponse.setStatus(statusCode);
        exceptionResponse.setSuccess(success);
        exceptionResponse.setTimeStamp(timeStamp);

        return exceptionResponse;

    }

}
