package com.codingshutttle.youtube.LearningAnnotations.LearningProfiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class ProdDataService implements DataService{
    @Override
    public void getData() {
        System.out.println("prod service");
    }
}
