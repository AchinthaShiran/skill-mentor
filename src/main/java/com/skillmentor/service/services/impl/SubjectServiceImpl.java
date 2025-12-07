package com.skillmentor.service.services.impl;

import com.skillmentor.service.dtos.subject.SubjectCreateRequestDTO;
import com.skillmentor.service.dtos.subject.SubjectPatchRequestDTO;
import com.skillmentor.service.entities.Subject;
import com.skillmentor.service.respositories.SubjectRepository;
import com.skillmentor.service.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;

    // TODO: Exception handling
    // TODO: Error Logging
    // TODO: Pagination

    @Override
    public List<Subject> getAllSubjects() {
        try {
            return subjectRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all subjects: " + e.getMessage(), e);
        }
    }

    @Override
    public Subject getSubjectById(Long id) {
        try {
            return subjectRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Subject not found with id: " + id));
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving subject with id " + id + ": " + e.getMessage(), e);
        }
    }

    @Override
    public Subject createSubject(SubjectCreateRequestDTO subjectDTO) {
        try {
            Subject subject = modelMapper.map(subjectDTO, Subject.class);
            return subjectRepository.save(subject);
        } catch (Exception e) {
            throw new RuntimeException("Error creating subject: " + e.getMessage(), e);
        }
    }

    @Override
    public Subject updateSubject(Long id, SubjectCreateRequestDTO subjectDTO) {
        try {
            Subject subject = getSubjectById(id);
            modelMapper.map(subjectDTO, subject);
            return subjectRepository.save(subject);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error updating subject with id " + id + ": " + e.getMessage(), e);
        }
    }

    @Override
    public Subject patchSubject(Long id, SubjectPatchRequestDTO subjectDTO) {
        try {
            Subject subject = getSubjectById(id);
            modelMapper.map(subjectDTO, subject);
            return subjectRepository.save(subject);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error patching subject with id " + id + ": " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteSubject(Long id) {
        try {
            subjectRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting subject with id " + id + ": " + e.getMessage(), e);
        }
    }

    @Override
    public List<Subject> getOrCreateSubjects(List<Subject> subjects) {
        try {
            return subjects.stream()
                    .map(subject -> {
                        if (subject.getId() != null) {
                            return getSubjectById(subject.getId());
                        } else if (subject.getName() != null) {
                            return subjectRepository.findByName(subject.getName())
                                    .orElseGet(() -> subjectRepository.save(subject));
                        } else {
                            throw new RuntimeException("Subject must have either id or name");
                        }
                    })
                    .collect(Collectors.toList());
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error getting or creating subjects: " + e.getMessage(), e);
        }
    }
}
