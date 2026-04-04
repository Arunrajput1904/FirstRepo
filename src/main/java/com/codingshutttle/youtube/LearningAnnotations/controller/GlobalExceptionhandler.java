package com.codingshutttle.youtube.LearningAnnotations.controller;

import com.codingshutttle.youtube.LearningAnnotations.CustomExceptions.StudentAlreadyExistsException;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionhandler {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ResponseBodycontroller> h(NoSuchElementException obj) {
        ResponseBodycontroller response = new ResponseBodycontroller(obj.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(StudentAlreadyExistsException.class)
    public ResponseEntity<ResponseBodycontroller> h(StudentAlreadyExistsException obj) {
        ResponseBodycontroller response = new ResponseBodycontroller(obj.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<HashMap<String,String>> hh(MethodArgumentNotValidException obj){
        HashMap<String,String> response=new HashMap<>();
        obj.getBindingResult().getFieldErrors().forEach(error -> {
            response.put(error.getField(),error.getDefaultMessage());
        });
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String,String>> hhh(RuntimeException obj){
        HashMap<String,String> response=new HashMap<>();
        response.put("Error occurred->","Internal Server error......");
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,String>> hhh(Exception obj){
        HashMap<String,String> response=new HashMap<>();
        response.put("Error occurred->","Internal Server error......");
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
