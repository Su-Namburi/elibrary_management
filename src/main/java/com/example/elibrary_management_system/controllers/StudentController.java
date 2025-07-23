package com.example.elibrary_management_system.controllers;

import com.example.elibrary_management_system.dtos.CreateStudentRequest;
import com.example.elibrary_management_system.dtos.GetStudentResponse;
import com.example.elibrary_management_system.dtos.UpdateStudentRequest;
import com.example.elibrary_management_system.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public Long addStudent(@RequestBody CreateStudentRequest createStudentRequest) {
        return this.studentService.createStudent(createStudentRequest.toStudent());
    }

    @GetMapping("/get")
    public GetStudentResponse getStudent(@RequestParam("id") Long id) {
        return this.studentService.getStudent(id);
    }

    @PatchMapping("/patch")
    public GetStudentResponse updateStudent(@RequestBody UpdateStudentRequest updateStudentRequest,
                                            @RequestParam("id") Long id) {
        return this.studentService.updateStudent(updateStudentRequest.toStudent(),id);
    }

    @DeleteMapping("/deactivate")
    public void deleteStudent(@RequestParam("id") Long id) {
        this.studentService.deactivate(id);
    }


}
