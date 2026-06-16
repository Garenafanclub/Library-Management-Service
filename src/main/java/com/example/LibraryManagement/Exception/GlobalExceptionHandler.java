package com.example.LibraryManagement.Exception;

import com.example.LibraryManagement.DTOs.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Result<Object>> handleBaseException(BaseException ex)
    {
      Result<Object> errorResult = new Result<>(
              ex.getMessage(),
           String.valueOf(ex.getStatus().value())
      );
      return ResponseEntity.status(ex.getStatus()).body(errorResult);
    }

    // Replace the existing validation handler with this cleaner Map version
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Result<Object>> handleValidationException(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        Result<Object> errorResult = new Result<>(
                errors.toString(),
                String.valueOf(HttpStatus.BAD_REQUEST.value())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

    // Catch general crashes (Database down, NullPointers, etc.)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result<Object>> handleGeneralException(Exception ex) {
        Result<Object> errorResult = new Result<>("500", "Internal Server Error: " + ex.getMessage());
        return ResponseEntity.internalServerError().body(errorResult);
    }
}
