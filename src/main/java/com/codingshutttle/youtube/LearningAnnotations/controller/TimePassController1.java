package com.codingshutttle.youtube.LearningAnnotations.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class TimePassController1 {
    @GetMapping("/get/user/data")
        public String  getdata(){
        return "user Aryan manhas";
        }

}
