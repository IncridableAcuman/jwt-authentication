package com.boot.backend.exception;

public class BadRequestExceptionHandler extends RuntimeException{
    public BadRequestExceptionHandler(String message){
        super(message);
    }
}
