package com.codingshutttle.youtube.LearningAnnotations.StudentConfigurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BasicConfigus {
    @Bean
    ModelMapper getBean(){
        return new ModelMapper();
    }
}
