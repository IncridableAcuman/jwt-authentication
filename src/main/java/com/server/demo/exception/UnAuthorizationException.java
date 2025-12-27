package com.server.demo.exception;

import org.springframework.http.HttpStatus;

public class UnAuthorizationException extends ApiException{
    public UnAuthorizationException(String message){
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
