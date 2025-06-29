package com.boot.backend.exception;

public class ValidationError extends RuntimeException{
    public ValidationError(String message){
        super(message);
    }
}
