package com.codingshutttle.youtube.LearningAnnotations.service;

import com.codingshutttle.youtube.LearningAnnotations.DTO.StudentDTO;
import com.codingshutttle.youtube.LearningAnnotations.DTO.StudentDataUpdateDto;
import com.codingshutttle.youtube.LearningAnnotations.entity.Student;
import com.codingshutttle.youtube.LearningAnnotations.repository.Strudentrepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@Slf4j
@ExtendWith(MockitoExtension.class)
class StudentServiceimplTest {
    @Mock
    private Strudentrepository strudentrepository;
    Student student=Student.builder().name("Aryan").id(1L).email("Aryan12343@gmail.com").rollno(234L).build();
    @InjectMocks
    private StudentServiceimpl studentServiceimpl;
    @Test
    void getStudentById_whenStudent_isPresent(){
        Student student=Student.builder().name("Aryan").id(1L).email("Aryan12343@gmail.com").rollno(234L).build();
        Long id=1L;
        when(strudentrepository.findById(id)).thenReturn(Optional.of(student));
         Student data=studentServiceimpl.getbyid(1L);

        Assertions.assertThat(data.getEmail()).isEqualTo("Aryan12343@gmail.com");

    }
    @Test
    void getStudentById_whenStudent_isNotPresent(){
        Student student=Student.builder().name("Aryan").id(1L).email("Aryan12343@gmail.com").rollno(234L).build();
        Long id=1L;
        when(strudentrepository.findById(id)).thenThrow(new NoSuchElementException("ghdhjsd"));
        try{
            Student data=studentServiceimpl.getbyid(1L);
        }
        catch(Exception ex){
            Assertions.assertThat(ex).isInstanceOf(NoSuchElementException.class);
            Assertions.assertThat(ex.getMessage()).isEqualTo("ghdhjsd");
        }

    }
    @Test
    void saveStudent_whenStudent_doNotExist(){
        Student student=Student.builder().name("Aryan").id(null).email("Aryan12343@gmail.com").rollno(234L).build();

        when(strudentrepository.getStudentByEmailId(anyString())).thenReturn(List.of());
        when(strudentrepository.save(any(Student.class))).thenReturn(student);

        Student data=studentServiceimpl.savedata(student);
        Assertions.assertThat(data.getEmail()).isEqualTo("Aryan12343@gmail.com");
        ArgumentCaptor<Student> captor=ArgumentCaptor.forClass(Student.class);
        verify(strudentrepository).save(captor.capture());
        Student s=captor.getValue();

       Assertions.assertThat(s.getEmail()).isEqualTo("Aryan12343@gmail.com");

    }
    @Test()
    void getAnExceptionThrown_whenStudent_idIsAlreadyFilled(){
      Assertions.assertThatThrownBy(()->studentServiceimpl.savedata(student)).isInstanceOf(RuntimeException.class).hasMessage("Student id is already set!!!!");
    }
    @Test()
    void getAnExceptionThrown_whileSavingAStudent_whoseEmailAlreadyExists(){
        Student s=Student.builder().name("Aryan").id(null).email("Aryan12343@gmail.com").rollno(234L).build();
        when(strudentrepository.getStudentByEmailId(anyString())).thenReturn(List.of(s));

        Assertions.assertThatThrownBy(()->studentServiceimpl.savedata(s)).isInstanceOf(RuntimeException.class).hasMessage("Student already exists!!!!");
    }
    @Test()
    void getAllStudents_whenStudent_Exist(){
        when(strudentrepository.findAll()).thenReturn(List.of(student));
        Assertions.assertThat(studentServiceimpl.getAllStudents()).isNotNull().first().isInstanceOf(StudentDTO.class);
        verify(strudentrepository).findAll();
    }
    @Test()
    void getAnsExceptionThrown_whenNoStudentExist(){
        when(strudentrepository.findAll()).thenReturn(List.of());
        Assertions.assertThatThrownBy(()->studentServiceimpl.getAllStudents()).isInstanceOf(RuntimeException.class).hasMessage("No Student Exist");
    }
    @Test()
    void getResponseEntity_whileUpdatingTheStudent(){
        StudentDataUpdateDto s=StudentDataUpdateDto.builder().name("Vansh").id(1L).email("Aryan@gmail.com").rollno(234L).build();
        when(strudentrepository.findById(student.getId())).thenReturn(Optional.of(student));
        Assertions.assertThat(studentServiceimpl.updatebyid(1L,s)).isNotNull();
        Assertions.assertThat(s.getId()).isNotNull();
    }


}