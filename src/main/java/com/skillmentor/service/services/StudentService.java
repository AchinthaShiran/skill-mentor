package com.skillmentor.service.services;


import com.skillmentor.service.dtos.student.StudentCreateRequestDTO;
import com.skillmentor.service.dtos.student.StudentPatchRequestDTO;
import com.skillmentor.service.entities.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    List<Student> getAllStudents();
    Student getStudentById(Long id);
    Student addStudent(StudentCreateRequestDTO studentDTO);
    Student updateStudent(Long id, StudentCreateRequestDTO studentDTO);
    Student patchStudent(Long id, StudentPatchRequestDTO studentDTO);
    void deleteStudent(Long id);


}
