package com.codingshutttle.youtube.LearningAnnotations.DTO;

import lombok.*;
import org.springframework.context.annotation.Fallback;

import java.util.Objects;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter

public class StudentDTO {
     Long id;
     String name;
     Long rollno;
     String email;
     @Override
     public boolean equals(Object o) {
          if (!(o instanceof StudentDTO that))
               return false;
         return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(rollno, that.rollno);
     }
     @Override
     public int hashCode() {
          return Objects.hash(id, name, rollno);
     }
}
