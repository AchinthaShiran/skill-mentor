package com.skillmentor.service.services;

import com.skillmentor.service.dtos.subject.SubjectCreateRequestDTO;
import com.skillmentor.service.dtos.subject.SubjectPatchRequestDTO;
import com.skillmentor.service.entities.Subject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubjectService {

    List<Subject> getAllSubjects();
    Subject getSubjectById(Long id);
    Subject createSubject(SubjectCreateRequestDTO subjectDTO);
    Subject updateSubject(Long id, SubjectCreateRequestDTO subjectDTO);
    Subject patchSubject(Long id, SubjectPatchRequestDTO subjectDTO);
    void deleteSubject(Long id);
    List<Subject> getOrCreateSubjects(List<Subject> subjects);

}
