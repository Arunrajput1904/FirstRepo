package com.codingshutttle.youtube.LearningAnnotations.service;

import com.codingshutttle.youtube.LearningAnnotations.entity.Student;
import com.codingshutttle.youtube.LearningAnnotations.repository.Strudentrepository;
import lombok.Builder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    Strudentrepository studentrepo;

    @InjectMocks
    StudentServiceimpl studentService;
    @Test
    public void getStudentbyid_test(){

        //Setting behaviour
        Student obj= Student.builder().id(1L).name("Aryan").email("Aryan123@gamil.com").rollno(45L).build();
        when(studentrepo.findById(1L)).thenReturn(Optional.of(obj));
        // get the result
        Student result = studentService.getbyid(1L);
        // checking
        assertEquals("Aryan",result.getName());
        assertEquals("Aryan123@gamil.com",result.getEmail());
        assertEquals(45L,result.getRollno());
        assertEquals(1L,result.getId());
    }
    @Test
    public void gettheStudentbyid_fails(){
        when(studentrepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class,()->studentService.getbyid(1L));
    }
    @Test
    public void findStudentByname_avaliable(){
        Student obj= Student.builder().id(1L).name("Aryan").email("Aryan123@gamil.com").rollno(45L).build();
        when(studentrepo.findByName("Aryan")).thenReturn(obj);
        Student result=studentService.findthroughname("Aryan");
        assertEquals(obj.getName(),result.getName());
    }
    @Test
    public void gettheStudentbyname_fails(){
        when(studentrepo.findByName("Aryan")).thenReturn(null);
        assertThrows(NoSuchElementException.class,()->studentService.findthroughname("Aryan"));
    }
    @Test
    public void getStudentBetween_ifavaliable(){
        Student obj1= Student.builder().id(1L).name("Aryan").email("Aryan123@gamil.com").rollno(45L).build();
        Student obj2= Student.builder().id(1L).name("arun").email("arun@gamil.com").rollno(12L).build();
        List<Student>mimck=new ArrayList<>(List.of(obj1,obj2));
        when(studentrepo.AllStudentsBetween(12,45)).thenReturn(new ArrayList<>(mimck));

        List<Student>list=studentService.getAllStudentsbetween(12,45);
        assertEquals(mimck,list);
    }


}