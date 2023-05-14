package com.msvc.usuario.exceptions;

public class ResourceNotFoundException extends BaseRequestException{

    public ResourceNotFoundException() {
        super("Recurso no encontrado en el servidor");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }}
