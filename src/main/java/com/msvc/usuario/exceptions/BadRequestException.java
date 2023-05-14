package com.msvc.usuario.exceptions;

public class BadRequestException extends BaseRequestException {

    public BadRequestException() {
        super("Ha ocurrido un error en su solicitud, revise la información proporcionada");
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, String errorCode) {
        super(message, errorCode);
    }
}
