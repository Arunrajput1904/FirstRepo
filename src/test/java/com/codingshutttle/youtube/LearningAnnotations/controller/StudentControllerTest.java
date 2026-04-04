package com.codingshutttle.youtube.LearningAnnotations.controller;

import com.codingshutttle.youtube.LearningAnnotations.DTO.StudentDTO;
import com.codingshutttle.youtube.LearningAnnotations.entity.Student;
import com.codingshutttle.youtube.LearningAnnotations.repository.Strudentrepository;
import org.apache.coyote.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class StudentControllerTest {
    @Autowired()
    private WebTestClient webTestClient;
    @Autowired()
    private Strudentrepository studentRepo;
    private Student teststudent = null;
   private Student TestStudent_failure=null;
    @Autowired
    private ModelMapper modelMapper;

    @BeforeEach()
    void setUp() {
        teststudent = Student.builder()
                .email("aryan123@gmail.com").
                rollno(12L).
                name("Aryan").
                build();
       TestStudent_failure=Student.builder()
                .email("vansh133@gmail.com").
                rollno(56L).
                name("Vansh").
                build();
        studentRepo.deleteAll();
    }

    @Test
    void testingConnection() {

    }

    @Test
    void getStudent_success() {
        Student student = studentRepo.save(teststudent);
        StudentDTO expectedStudent = StudentDTO.builder()
                .name("Aryan").
                rollno(12L).
                id(student.getId()).email(student.getEmail())
                .build();
        webTestClient.get().uri("/student").
                exchange().expectStatus().isOk().
                expectBodyList(StudentDTO.class).value(c -> {
                    StudentDTO data = c.get(0);
                    Assertions.assertThat(data).isEqualTo(expectedStudent);
//                   Assertions.assertThat(data.getName()).isEqualTo("Aryan");
//                   Assertions.assertThat(data.getRollno()).isEqualTo(12L);
//                   Assertions.assertThat(data.getId()).isEqualTo(student.getId());
                });
    }
    @Test
    void getStudent_failure() {
        webTestClient.get().uri("/student").
                exchange().
                expectStatus().is5xxServerError().
                expectBody(new ParameterizedTypeReference<Map<String,String>>() {})
                .value(c->{
                   Assertions.assertThat(c.get("Error occurred->")).isEqualTo("Internal Server error......");
                });

    }
    @Test
    void getStudent_withNameAndRollNo_Success(){
        Student student = studentRepo.save(teststudent);
        webTestClient.get().
                uri("/student/name/{name}/rollno/{rollno}","Aryan",12L).
                exchange().expectStatus().isOk().
                expectBody(Student.class).value(s->{
                    Assertions.assertThat(s).isEqualTo(student);
                });
    }
    @Test
    void getInternalServerError_withNameAndRollNoFetchingMethodToGetStudent_failure(){
        webTestClient.get().uri("/student/name/{name}/rollno/{rollno}","Vansh",34L).
                exchange().
                expectStatus().is4xxClientError().
                expectBody(new ParameterizedTypeReference<ResponseBodycontroller>() {})
                .value(c->{
                    Assertions.assertThat(c.message).isEqualTo("Student do not Exist");
                });
    }
    @Test
    void addStudent_success(){

        webTestClient.post().uri("/add").
                bodyValue(teststudent).exchange().expectStatus().isCreated().
                expectBody(Student.class).
                value(s->{
                    Assertions.assertThat(s.getEmail()).isEqualTo(teststudent.getEmail());
                    Assertions.assertThat(s.getRollno()).isEqualTo(teststudent.getRollno());
                    Assertions.assertThat(s.getName()).isEqualTo(teststudent.getName());
                });
    }
    @Test
    void addStudent_failure(){
        Student student = studentRepo.save(teststudent);
        webTestClient.post().uri("/add").
                bodyValue(teststudent).exchange().expectStatus().isBadRequest().
                expectBody(ResponseBodycontroller.class).
                value(s->{
                   s.getMessage().equals("Student id is already set!!!!");
                });
    }




}