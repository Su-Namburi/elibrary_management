package com.example.elibrary_management_system.services;

import com.example.elibrary_management_system.models.Admin;
import com.example.elibrary_management_system.models.Authority;
import com.example.elibrary_management_system.models.User;
import com.example.elibrary_management_system.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    UserService userService;

    @Autowired
    AdminRepository adminRepository;

    public Long createAdmin(Admin admin) {
        User user = this.userService.createUser(admin.getUser(),Authority.ADMIN);
        admin.setUser(user);

        return this.adminRepository.save(admin).getId();
    }
}
