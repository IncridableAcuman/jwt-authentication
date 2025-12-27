package com.server.demo.exception;

import com.server.demo.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;

@RestControllerAdvice
@Builder
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> apiExceptionHandler(ApiException e,HttpServletRequest request){
        return buildErrorResponse(e.getStatus(), e.getMessage(), request);
    }
    private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status,String message,HttpServletRequest request){
        ErrorResponse response = ErrorResponse.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(request.getRequestURI())
                .dateTime(OffsetDateTime.now())
                .build();
        return ResponseEntity.status(status).body(response);
    }
}
