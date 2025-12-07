package com.skillmentor.service.controllers.v1;

import com.skillmentor.service.controllers.AbstractController;
import com.skillmentor.service.dtos.subject.SubjectCreateRequestDTO;
import com.skillmentor.service.dtos.subject.SubjectPatchRequestDTO;
import com.skillmentor.service.entities.Subject;
import com.skillmentor.service.services.SubjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/subjects")
@Slf4j
@RequiredArgsConstructor
public class SubjectController extends AbstractController {

    private final SubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects() {
        return sendOkResponse(subjectService.getAllSubjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Long id) {
        return sendOkResponse(subjectService.getSubjectById(id));
    }

    @PostMapping
    public ResponseEntity<Subject> createSubject(@Validated @RequestBody SubjectCreateRequestDTO subjectDTO) {
        return sendCreatedResponse(subjectService.createSubject(subjectDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable Long id, @Validated @RequestBody SubjectCreateRequestDTO subjectDTO) {
        return sendOkResponse(subjectService.updateSubject(id, subjectDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Subject> patchSubject(@PathVariable Long id, @RequestBody SubjectPatchRequestDTO subjectDTO) {
        return sendOkResponse(subjectService.patchSubject(id, subjectDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
        return sendNoContentResponse();
    }
}
