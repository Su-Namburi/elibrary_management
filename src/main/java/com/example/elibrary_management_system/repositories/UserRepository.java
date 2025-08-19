package com.example.elibrary_management_system.repositories;

import com.example.elibrary_management_system.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
