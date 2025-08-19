package com.example.elibrary_management_system.services;


import com.example.elibrary_management_system.dtos.GetStudentResponse;
import com.example.elibrary_management_system.models.Student;
import com.example.elibrary_management_system.models.StudentStatus;
import com.example.elibrary_management_system.repositories.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.json.simple.JSONObject;

import java.util.Iterator;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ObjectMapper mapper;

    public Long createStudent(Student student) {
        /*
        * 1. encode password
        * 2. add authority
        * 3. update/create user
        * 4. link student and user
        */
        return this.studentRepository.save(student).getId();
    }

    public GetStudentResponse getStudent(Long id) {
        Student student = this.studentRepository.findById(id).orElse(null);
        return GetStudentResponse.builder().student(student).build();
    }

    public GetStudentResponse updateStudent(Student student, Long id) {

        Student existingStudent = this.studentRepository.findById(id).orElse(null);

        Student updatedStudent = this.merge(existingStudent, student);

        Student s = this.studentRepository.save(updatedStudent);

        return GetStudentResponse.builder().student(updatedStudent).build();

    }

    private Student merge(Student existing, Student incoming) {

        JSONObject incomingStudent = mapper.convertValue(incoming, JSONObject.class);
        JSONObject savedStudent = mapper.convertValue(existing, JSONObject.class);

        Iterator it = incomingStudent.keySet().iterator();  // id, name, email, mobile .....
        while (it.hasNext()) {
            String key = (String) it.next();
            if (incomingStudent.get(key) != null) {
                savedStudent.put(key, incomingStudent.get(key));
            }
        }
        Student updatedStudent = mapper.convertValue(savedStudent, Student.class);
        return updatedStudent;
    }

    public void deactivate(Long id) {
        Student student = this.studentRepository.findById(id).orElse(null);
        if (student != null) {
            this.studentRepository.deactivate(student.getId(), StudentStatus.INACTIVE);
        }
    }
}
