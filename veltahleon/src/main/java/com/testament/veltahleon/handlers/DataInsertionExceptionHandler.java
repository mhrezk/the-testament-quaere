package com.testament.veltahleon.handlers;

import com.testament.veltahleon.exceptions.DataInsertionException;
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
public class DataInsertionExceptionHandler {

    @ExceptionHandler(DataInsertionException.class)
    public ResponseEntity<CustomResponse> handleException(DataInsertionException exception) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", exception.getMessage() + " already exists! Duplicate entry is disallowed!");

        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorData(errorMap)
                .issue("Validation error!")
                .build());
    }
}
