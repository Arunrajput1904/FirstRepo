package com.codingshutttle.youtube.LearningAnnotations.LearningProfiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class DevDataService implements DataService{
    @Override
    public void getData() {
        System.out.println("dev service");
    }
}
