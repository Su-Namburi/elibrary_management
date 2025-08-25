package com.example.elibrary_management_system.controllers;

import com.example.elibrary_management_system.dtos.CreateAdminRequest;
import com.example.elibrary_management_system.dtos.CreateStudentRequest;
import com.example.elibrary_management_system.dtos.GetStudentResponse;
import com.example.elibrary_management_system.services.AdminService;
import com.example.elibrary_management_system.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public Long addAdmin(@RequestBody CreateAdminRequest createAdminRequest) {
        return this.adminService.createAdmin( createAdminRequest.toAdmin());
    }


}
