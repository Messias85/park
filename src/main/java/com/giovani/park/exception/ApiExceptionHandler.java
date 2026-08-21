package com.giovani.park.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidException(
            MethodArgumentNotValidException ex, 
            HttpServletRequest request,
            BindingResult result) {

        ErrorMessage error = new ErrorMessage(
            request,
            HttpStatus.UNPROCESSABLE_ENTITY,
            "Campo(s) invalido(s)",
            result
        );

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(error);
    }
    
 

        @ExceptionHandler(UsernameUniqueViolationException.class)
        public ResponseEntity<ErrorMessage> uniqueViolationException(RuntimeException ex, HttpServletRequest request) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new ErrorMessage(request, HttpStatus.CONFLICT, ex.getMessage()));
        }
        
        @ExceptionHandler(PasswordInvalidException.class)
        public ResponseEntity<ErrorMessage> handlePasswordInvalidException(
                PasswordInvalidException ex, 
                HttpServletRequest request) {

            ErrorMessage message = new ErrorMessage(request, HttpStatus.BAD_REQUEST, ex.getMessage());
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        
     
    
}