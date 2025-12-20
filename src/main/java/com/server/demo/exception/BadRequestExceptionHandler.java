package com.server.demo.exception;

public class BadRequestExceptionHandler extends RuntimeException{
    public BadRequestExceptionHandler(String message){
        super(message);
    }
}
