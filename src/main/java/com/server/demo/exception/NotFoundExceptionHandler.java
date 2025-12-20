package com.server.demo.exception;

public class NotFoundExceptionHandler extends RuntimeException{
    public NotFoundExceptionHandler(String message){
        super(message);
    }
}
