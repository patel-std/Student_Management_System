package com.operation.collage.GlobalExceptions;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice 
public class GlobalException {
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> runtimeException(RuntimeException ex){
        return new ResponseEntity<>(ex.getMessage()+"Something Went Wrong",HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exception(Exception ex){
        return new ResponseEntity<>(ex.getMessage()+"Database issue",HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> illegalArgument(IllegalArgumentException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> authenticationException(AuthenticationException ex){
        return new ResponseEntity<>(ex.getMessage()+"Invalid accessbilities",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
