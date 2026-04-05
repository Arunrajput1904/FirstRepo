package com.codingshutttle.youtube.LearningAnnotations.service;

import com.codingshutttle.youtube.LearningAnnotations.DTO.StudentDTO;
import com.codingshutttle.youtube.LearningAnnotations.DTO.StudentDataUpdateDto;
import com.codingshutttle.youtube.LearningAnnotations.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {
     List<StudentDTO> getAllStudents();
     Student savedata(Student data);
     Student getbyid(Long id);
     void deletebyid(Long id);
     ResponseEntity<Student> updatebyid(long id, StudentDataUpdateDto obj);
     public Student findthroughname(String name);
     public Student findbynameandrollno(String name ,Long rollno);
     public List<String> findAllStudentnames();
     public List<Integer> findAllStudentrollNoByname(List<String >names);
     public List<Student> getAllStudentsbetween(int start,int end);

     @Query("select e from Student e ")
public List<Student> getallstudent();
}
