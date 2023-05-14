package com.msvc.usuario.responses;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiExceptionResponse {

    private String msg;
    private Boolean success;
    private HttpStatus status;
    private LocalDateTime timeStamp;
    private String errorCode;
}
