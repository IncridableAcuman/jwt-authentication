package com.server.demo.exception;

public class UnAuthorizationExceptionHandler extends RuntimeException{
    public UnAuthorizationExceptionHandler(String message){
        super(message);
    }
}
