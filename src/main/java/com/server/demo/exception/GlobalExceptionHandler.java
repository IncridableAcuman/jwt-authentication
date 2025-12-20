package com.server.demo.exception;

import com.server.demo.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestExceptionHandler.class)
    public ResponseEntity<ErrorResponse> badRequest(BadRequestExceptionHandler e, HttpServletRequest request){
        ErrorResponse response = new ErrorResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        response.setMessage(e.getMessage());
        response.setPath(request.getRequestURI());
        response.setDateTime(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    @ExceptionHandler(NotFoundExceptionHandler.class)
    public ResponseEntity<ErrorResponse> notFound(NotFoundExceptionHandler e,HttpServletRequest request){
        ErrorResponse response = new ErrorResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        response.setMessage(e.getMessage());
        response.setPath(request.getRequestURI());
        response.setDateTime(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    @ExceptionHandler(UnAuthorizationExceptionHandler.class)
    public ResponseEntity<ErrorResponse> unAuthorization(UnAuthorizationExceptionHandler e,HttpServletRequest request){
        ErrorResponse response = new ErrorResponse();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setError(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        response.setMessage(e.getMessage());
        response.setPath(request.getRequestURI());
        response.setDateTime(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
