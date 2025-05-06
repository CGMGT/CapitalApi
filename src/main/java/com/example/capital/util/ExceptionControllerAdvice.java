package com.example.capital.util;

import com.example.capital.util.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity handleException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acceso Denegado");
    }

}
