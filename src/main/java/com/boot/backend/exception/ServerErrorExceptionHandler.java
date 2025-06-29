package com.boot.backend.exception;

public class ServerErrorExceptionHandler extends RuntimeException{
    public ServerErrorExceptionHandler(String message){
        super(message);
    }
}
