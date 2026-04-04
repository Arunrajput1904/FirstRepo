package com.codingshutttle.youtube.LearningAnnotations.LearningProfiles;

import org.springframework.stereotype.Service;



public class DefaultDataService implements DataService{
    @Override
    public void getData() {
        System.out.println("Global Service ");
    }
}
