package com.codingshutttle.youtube.LearningAnnotations.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
    @NotBlank(message = "Name is required", groups = CreateGroup.class)
   private String name;
    @Min(message = "Roll no should be greater than 1", groups = {CreateGroup.class, UpdateGroup.class}, value = 1)
   private Long rollno;
    @Email(message="Email is required....",groups= CreateGroup.class)
    @NotBlank(message="Email cannot be empty....",groups= {UpdateGroup.class, CreateGroup.class})
    private String email;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Student student))
            return false;
        return Objects.equals(id, student.id) && Objects.equals(name, student.name) && Objects.equals(rollno, student.rollno) && Objects.equals(email, student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rollno, email);
    }
}
