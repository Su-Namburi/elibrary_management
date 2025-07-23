package com.example.elibrary_management_system.dtos;

import com.example.elibrary_management_system.models.Student;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetStudentResponse {

    Student student;
}
