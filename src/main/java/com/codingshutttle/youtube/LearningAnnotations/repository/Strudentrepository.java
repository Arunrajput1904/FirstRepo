package com.codingshutttle.youtube.LearningAnnotations.repository;

import com.codingshutttle.youtube.LearningAnnotations.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

import static javax.swing.text.html.HTML.Tag.SELECT;
@Repository
public interface Strudentrepository extends JpaRepository<Student,Long> {
    public Student findByName(String name);
    public Student findByNameAndRollno(String name,Long rollno);
   @Query("SELECT obj.name FROM Student obj")
    public List<String> Allnames();
    @Query("SELECT obj.rollno FROM Student obj WHERE obj.name In(?1)")
    public List<Integer> findrollnoofAllbynames(List<String> names);
    @Query("SELECT obj FROM Student obj WHERE obj.rollno BETWEEN ?1 AND ?2")
    public List<Student> AllStudentsBetween(int start,int end);
    @Query("""
    select s from Student s
    where s.email=:email
""")
    List<Student> getStudentByEmailId(@Param("email") String email);
}
