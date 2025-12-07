package com.skillmentor.service.controllers.v1;

import com.skillmentor.service.controllers.AbstractController;
import com.skillmentor.service.dtos.student.StudentCreateRequestDTO;
import com.skillmentor.service.dtos.student.StudentPatchRequestDTO;
import com.skillmentor.service.entities.Student;
import com.skillmentor.service.services.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/students")
@RequiredArgsConstructor
@Slf4j
public class StudentController extends AbstractController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return sendOkResponse(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return sendOkResponse(studentService.getStudentById(id));
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@Validated @RequestBody StudentCreateRequestDTO studentDTO) {
        return sendCreatedResponse(studentService.addStudent(studentDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @Validated @RequestBody StudentCreateRequestDTO studentDTO) {
        return sendOkResponse(studentService.updateStudent(id, studentDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Student> patchStudent(@PathVariable Long id, @RequestBody StudentPatchRequestDTO studentDTO) {
        return sendOkResponse(studentService.patchStudent(id, studentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return sendNoContentResponse();
    }

}
