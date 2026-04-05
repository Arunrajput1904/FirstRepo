package com.codingshutttle.youtube.LearningAnnotations.controller;

import com.codingshutttle.youtube.LearningAnnotations.DTO.StudentDTO;
import com.codingshutttle.youtube.LearningAnnotations.DTO.StudentDataUpdateDto;
import com.codingshutttle.youtube.LearningAnnotations.entity.CreateGroup;
import com.codingshutttle.youtube.LearningAnnotations.entity.Student;
import com.codingshutttle.youtube.LearningAnnotations.entity.UpdateGroup;
import com.codingshutttle.youtube.LearningAnnotations.repository.Strudentrepository;
import com.codingshutttle.youtube.LearningAnnotations.service.StudentService;
import jakarta.annotation.Nonnull;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
public class StudentController {
    private final StudentService studentservice;

    private final Logger logger =LoggerFactory.getLogger(StudentController.class);
    @GetMapping("/student")
    public List<StudentDTO> getStudent(){
        logger.info("/student api running ");
        return studentservice.getAllStudents();
    }
    @GetMapping("/say")
    public void say(){
        logger.info("/say api running ");
        System.out.println( "hello aryan jhj hhhh");
    }
//    @GetMapping("/student/{id}")
//    public Student fetchbyid(@PathVariable Long id){
//        logger.info("/say api running ");
//        return   studentservice.getbyid(id);
//    }
@GetMapping("/student/{id}")
       public Student addbyid(@PathVariable Long id){
        return studentservice.getbyid(id);

}
    @GetMapping("/student/name/{name}")
    public Student fetchbyname(@PathVariable String name){
        logger.info("/student/name/{} is called",name);
        return  studentservice.findthroughname(name);
    }
    @GetMapping("/student/name/{name}/rollno/{rollno}")
    public Student fetchbynameandrollno(@PathVariable String name,@PathVariable Long rollno){
        return  studentservice.findbynameandrollno(name,rollno);
    }
    @PostMapping("/add")
    public ResponseEntity<Student> add(@Validated(CreateGroup.class) @RequestBody Student data){
        logger.info("/add api called successfully and adding {} given data... ",data);
      Student response= studentservice.savedata(data);
      return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public String deletebyid(@PathVariable Long id){
        studentservice.deletebyid(id);
        return "Data deleted successfully";
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Student>  updatebyid(@Validated(UpdateGroup.class)@PathVariable("id")Long id, @RequestBody StudentDataUpdateDto obj){
        return this.studentservice.updatebyid(id,obj);
    }
    @GetMapping("/student/allnames")
    public List<String> AllStudentnames(){
        return studentservice.findAllStudentnames();
    }
    @GetMapping("/student/allrollnobyname")
    public List<Integer> AllStudentnames(@RequestBody List<String >names){
        return studentservice.findAllStudentrollNoByname(names);
    }
    @GetMapping("/student/findStudentbetween/{start}/{end}")
    public List<Student> findStudentsbetween(@PathVariable int start,@PathVariable int end ){
        return studentservice.getAllStudentsbetween( start,end);
    }

    @GetMapping("/student/getall")
    public List<Student> getall(){
        return studentservice.getallstudent();
    }
}
