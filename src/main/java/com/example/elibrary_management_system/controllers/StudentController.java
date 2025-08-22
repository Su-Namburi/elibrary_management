package com.example.elibrary_management_system.controllers;

import com.example.elibrary_management_system.dtos.CreateStudentRequest;
import com.example.elibrary_management_system.dtos.GetStudentResponse;
import com.example.elibrary_management_system.dtos.UpdateStudentRequest;
import com.example.elibrary_management_system.models.User;
import com.example.elibrary_management_system.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public Long addStudent(@RequestBody CreateStudentRequest createStudentRequest) {
        return this.studentService.createStudent(createStudentRequest.toStudent());
    }

    @GetMapping("/admin/get")
    public GetStudentResponse getStudentByAdmin(@RequestParam("id") Long id) {
        return this.studentService.getStudent(id);
    }

    @GetMapping("/get")
    public GetStudentResponse getStudent() {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        User user = (User) authentication.getPrincipal();
        Long id = user.getStudent().getId();
        return this.studentService.getStudent(id);
    }

    @PatchMapping("/patch")
    public GetStudentResponse updateStudent(@RequestBody UpdateStudentRequest updateStudentRequest) {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        User user = (User) authentication.getPrincipal();
        Long id = user.getStudent().getId();
        return this.studentService.updateStudent(updateStudentRequest.toStudent(),id);
    }

    @DeleteMapping("/deactivate")
    public void deleteStudent() {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        User user = (User) authentication.getPrincipal();
        Long id = user.getStudent().getId();
        this.studentService.deactivate(id);
    }


}
