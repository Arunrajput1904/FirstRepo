package com.codingshutttle.youtube.LearningAnnotations.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseBodycontroller {
    String message;
    HttpStatus status;

}
