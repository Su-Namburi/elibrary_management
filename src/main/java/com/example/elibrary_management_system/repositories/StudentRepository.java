package com.example.elibrary_management_system.repositories;

import com.example.elibrary_management_system.models.Student;
import com.example.elibrary_management_system.models.StudentStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Transactional
    @Modifying
    @Query("update Student s set s.status=?2 where s.id=?1")
    void deactivate(Long id, StudentStatus status);
}
