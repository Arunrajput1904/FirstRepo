package com.codingshutttle.youtube.LearningAnnotations;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.shaded.com.github.dockerjava.core.DockerContextMetaFile;
import org.testcontainers.utility.DockerImageName;
@TestConfiguration
public class TestConfigurations {

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgreSQLContainer() {
        return new PostgreSQLContainer<>("postgres:16");
    }

}