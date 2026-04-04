package com.codingshutttle.youtube.LearningAnnotations.CustomExceptions;

public class StudentAlreadyExistsException extends RuntimeException {
    public StudentAlreadyExistsException(String message) {
        super(message);
    }
}
