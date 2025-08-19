package com.example.elibrary_management_system.dtos;

import com.example.elibrary_management_system.models.Gender;
import com.example.elibrary_management_system.models.Student;
import com.example.elibrary_management_system.models.StudentStatus;
import com.example.elibrary_management_system.models.User;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateStudentRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

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
                .user(User.builder()
                        .username(username)
                        .password(password)
                        .build())
                .build();
    }
}
