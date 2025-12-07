package com.skillmentor.service.services.impl;


import com.skillmentor.service.dtos.student.StudentCreateRequestDTO;
import com.skillmentor.service.dtos.student.StudentPatchRequestDTO;
import com.skillmentor.service.dtos.user.UserCreateRequestDTO;
import com.skillmentor.service.entities.Student;
import com.skillmentor.service.entities.User;
import com.skillmentor.service.entities.UserType;
import com.skillmentor.service.exceptions.SkillMentorException;
import com.skillmentor.service.respositories.StudentRepository;
import com.skillmentor.service.services.StudentService;
import com.skillmentor.service.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    // TODO: Exception handling
    // TODO: Error Logging
    // TODO: Pagination

    @Override
    public List<Student> getAllStudents() {
        try {
            return studentRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all students: " + e.getMessage(), e);
        }
    }

    @Override
    public Student getStudentById(Long id) {
        try {
            return studentRepository.findById(id)
                    .orElseThrow(() -> new SkillMentorException("Student not found with id", HttpStatus.NOT_FOUND));
        } catch (SkillMentorException skillMentorException) {
            log.error("User not found with id: {}",id, skillMentorException);
            throw skillMentorException;
        } catch (Exception exception) {
            log.error("Failed to get student with id: {}", id, exception);
            throw new SkillMentorException("Failed to get student with id", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Student addStudent(StudentCreateRequestDTO studentDTO) {
        try {
            // Create user first
            UserCreateRequestDTO userDTO = modelMapper.map(studentDTO, UserCreateRequestDTO.class);
            userDTO.setUserType(UserType.STUDENT);
            
            User user = userService.createUser(userDTO);
            
            // Create student with the user
            Student student = modelMapper.map(studentDTO, Student.class);
            student.setUser(user);
            
            return studentRepository.save(student);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error adding student: " + e.getMessage(), e);
        }
    }

    @Override
    public Student updateStudent(Long id, StudentCreateRequestDTO studentDTO) {
        try {
            Student student = getStudentById(id);
            modelMapper.map(studentDTO, student);
            return studentRepository.save(student);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error updating student with id " + id + ": " + e.getMessage(), e);
        }
    }

    @Override
    public Student patchStudent(Long id, StudentPatchRequestDTO studentDTO) {
        try {
            Student student = getStudentById(id);
            // set model mapper configs so that it will ignore null values when mapping
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
            modelMapper.getConfiguration().setSkipNullEnabled(true);
            modelMapper.map(studentDTO, student);
            return studentRepository.save(student);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error patching student with id " + id + ": " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteStudent(Long id) {
        try {
            studentRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting student with id " + id + ": " + e.getMessage(), e);
        }
    }
}
