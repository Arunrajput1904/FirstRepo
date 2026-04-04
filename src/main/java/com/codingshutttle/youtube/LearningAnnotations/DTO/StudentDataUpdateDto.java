package com.codingshutttle.youtube.LearningAnnotations.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StudentDataUpdateDto {
    Long id;
    String name;
    Long rollno;
    String email;
}
