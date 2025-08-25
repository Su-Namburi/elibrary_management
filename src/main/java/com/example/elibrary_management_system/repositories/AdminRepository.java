package com.example.elibrary_management_system.repositories;

import com.example.elibrary_management_system.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {

}
