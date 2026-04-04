package com.codingshutttle.youtube.LearningAnnotations.service;

import com.codingshutttle.youtube.LearningAnnotations.CustomExceptions.StudentAlreadyExistsException;
import com.codingshutttle.youtube.LearningAnnotations.DTO.StudentDTO;
import com.codingshutttle.youtube.LearningAnnotations.DTO.StudentDataUpdateDto;
import com.codingshutttle.youtube.LearningAnnotations.entity.Student;
import com.codingshutttle.youtube.LearningAnnotations.repository.Strudentrepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
@Slf4j
@Service
@AllArgsConstructor

public class StudentServiceimpl implements StudentService {
    private final Strudentrepository studentrepo;
    private final ModelMapper mapper;
    @Override
    public List<StudentDTO> getAllStudents() {
        log.debug("Entered the service");
        List<Student> temp = studentrepo.findAll();
        if(temp.isEmpty()){
            log.debug("Got an exception ");
            throw new RuntimeException("No Student Exist");
        }
        return temp.stream().map(student -> new StudentDTO(student.getId(), student.getName(),student.getRollno(),student.getEmail())).toList();
    }

    @Override
    public Student savedata(Student data) {
//        if(data.getId()!=null){
//            throw new RuntimeException("Student id is already set!!!!");
//        }
        List<Student> list=studentrepo.getStudentByEmailId(data.getEmail());
        if(!list.isEmpty()){
            throw new StudentAlreadyExistsException("Student already exists!!!!");
        }
        return studentrepo.save(data);
    }

    @Override
    public Student getbyid(Long id) {
        Optional<Student> response= studentrepo.findById(id);
        if(response.isEmpty()){
            throw new NoSuchElementException("No such data exist for:"+id);
        }
        return response.get();
    }

    @Override
    public void deletebyid(Long id) {
        studentrepo.deleteById(id);
    }

    @Override
    public ResponseEntity<Student> updatebyid(long id, StudentDataUpdateDto obj) {

        Optional<Student> check=studentrepo.findById(id);

        if(check.isEmpty()){
           throw new NoSuchElementException("No data found for id:"+id);
        }
        Student data=check.get();
        if (Objects.nonNull(obj.getName()) && !"".equalsIgnoreCase(obj.getName())) {
            data.setName(obj.getName());
        }
        if (Objects.nonNull(obj.getId())) {
            data.setId(obj.getId());
        }
        if (Objects.nonNull(obj.getRollno())) {
            data.setRollno(obj.getRollno());
        }
        if(Objects.nonNull(obj.getEmail())&&!"".equalsIgnoreCase(obj.getEmail())){
            data.setEmail(obj.getEmail());
        }
      //  obj.setId(null);
        studentrepo.save(data);
        Optional<Student> updated=studentrepo.findById(id);
   //     obj.setId(null);
        return new ResponseEntity<>(updated.get(), HttpStatus.CREATED);
    }
    @Override
    public Student findthroughname(String name) {
        Student response=studentrepo.findByName(name);
        if(response==null){
            throw new NoSuchElementException("No data exist for this name......"+name);
        }
        return response;
    }
    @Override
    public Student findbynameandrollno(String name, Long rollno) {
        Student student= student=studentrepo.findByNameAndRollno(name ,rollno);
        if(student==null){
            throw new NoSuchElementException("Student do not Exist");
        }
     return student;
    }

    @Override
    public List<String> findAllStudentnames() {
        return studentrepo.Allnames();
    }

    @Override
    public List<Integer> findAllStudentrollNoByname(List<String> names) {
        return studentrepo.findrollnoofAllbynames(names);
    }

    @Override
    public List<Student> getAllStudentsbetween(int start, int end) {
        return studentrepo.AllStudentsBetween(start,end);
    }


}
