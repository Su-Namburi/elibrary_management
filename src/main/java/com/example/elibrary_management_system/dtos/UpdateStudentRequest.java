package com.example.elibrary_management_system.dtos;

import com.example.elibrary_management_system.models.Gender;
import com.example.elibrary_management_system.models.Student;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateStudentRequest {

    private String studentName;

    private Gender gender;

    private String mobile;

    private Integer age;

    public Student toStudent(){
        return Student.builder()
                .studentName(studentName)
                .gender(gender)
                .mobile(mobile)
                .age(age)
                .build();
    }


}