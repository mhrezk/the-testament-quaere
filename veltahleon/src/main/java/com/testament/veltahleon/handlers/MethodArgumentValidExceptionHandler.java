package com.testament.veltahleon.handlers;

import com.testament.veltahleon.responses.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MethodArgumentValidExceptionHandler {

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleException(MethodArgumentNotValidException exception) {
//        Map<String, String> errorMap = new HashMap<>();
//        exception.getBindingResult().getFieldErrors().forEach(error -> {
//            errorMap.put(error.getField(), error.getDefaultMessage());
//        });
//        return errorMap;
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomResponse> handleException(MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity.internalServerError().body(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST)
                .errorData(errorMap)
                .issue("Validation error! Validation constraints are not met!")
                .build());
    }
}
