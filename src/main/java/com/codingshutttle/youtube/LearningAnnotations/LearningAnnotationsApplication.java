package com.codingshutttle.youtube.LearningAnnotations;
import com.codingshutttle.youtube.LearningAnnotations.LearningProfiles.DataService;
import com.codingshutttle.youtube.LearningAnnotations.LearningProfiles.DevDataService;
import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.CommandLinePropertySource;

@SpringBootApplication
public class LearningAnnotationsApplication implements CommandLineRunner {
	@Autowired
	private DataService dataService;
	@Value("${my.data}")
	private String data;
	public static void main(String[] args) {
		SpringApplication.run(LearningAnnotationsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(data);
		dataService.getData();
	}
}