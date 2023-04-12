package com.springapi.springwear.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String > sQLException(SQLException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getClass().getName());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> runTimeException(RuntimeException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass().getName());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exception(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass().getName());
    }
}
