package com.example.elibrary_management_system.dtos;

import com.example.elibrary_management_system.models.Gender;
import com.example.elibrary_management_system.models.Student;
import com.example.elibrary_management_system.models.StudentStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateStudentRequest {

    private String studentName;

    private Gender gender;

    @NotBlank
    private String mobile;

    private Integer age;

    public Student toStudent() {
        return Student
                .builder()
                .studentName(studentName)
                .gender(gender)
                .mobile(mobile)
                .age(age)
                .status(StudentStatus.ACTIVE)
                .build();
    }
}
