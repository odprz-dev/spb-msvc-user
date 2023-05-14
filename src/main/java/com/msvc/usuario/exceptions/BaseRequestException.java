package com.msvc.usuario.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BaseRequestException extends RuntimeException {
    LocalDateTime timeStamp = LocalDateTime.now();
    String errorCode;

    BaseRequestException(String message) {
        super(message);
    }

    BaseRequestException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
