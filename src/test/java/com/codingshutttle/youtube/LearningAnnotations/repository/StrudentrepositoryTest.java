package com.codingshutttle.youtube.LearningAnnotations.repository;

import com.codingshutttle.youtube.LearningAnnotations.TestConfigurations;
import com.codingshutttle.youtube.LearningAnnotations.entity.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StrudentrepositoryTest {

    @Autowired
    private Strudentrepository studentRepository;
    Student AddStudent(){
       return  Student.builder().name("Aryan").email("addie123@gmail.com").rollno(12L).build();
    }
    @Test
    void getStudentByEmailId_studentExist() {
        Student student = AddStudent();

        studentRepository.save(student);

        List<Student>students= studentRepository.getStudentByEmailId("addie123@gmail.com");

        Assertions.assertThat(students).isNotNull().isNotEmpty().actual().get(0).getEmail().equals("addie123@gmail.com");
    }
    @Test
    void getStudentByEmail_studentDoNotExist(){
        List<Student>students= studentRepository.getStudentByEmailId("addie123@gmail.com");

        Assertions.assertThat(students).isEmpty();
    }
}