package com.skillmentor.service.controllers.v1;

import com.skillmentor.service.controllers.AbstractController;
import com.skillmentor.service.dtos.MentorDTO;
import com.skillmentor.service.dtos.subject.SubjectIdsRequestDTO;
import com.skillmentor.service.entities.Mentor;
import com.skillmentor.service.services.MentorService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/mentors")
@Slf4j
@RequiredArgsConstructor
public class MentorController extends AbstractController {

    private final MentorService mentorService;
    private final ModelMapper modelMapper;

//    This is being handled by the @RequiredArgsConstructor
//    public MentorController(MentorService mentorService, ModelMapper modelMapper) {
//        this.mentorService = mentorService;
//        this.modelMapper = modelMapper;
//    }

    @GetMapping
    public ResponseEntity<Page<Mentor>> getAllMentors(Pageable pageable) {
        return sendOkResponse(mentorService.getAllMentors(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mentor> getMentorById(@PathVariable Long id) {
        return sendOkResponse(mentorService.getMentorById(id));
    }

    @PostMapping
    public ResponseEntity<Mentor> createMentor(@Validated @RequestBody MentorDTO mentorDTO) {
        // Mapping using Model Mapper
        Mentor createdMentor = mentorService.createMentor(mentorDTO);
        return sendCreatedResponse(createdMentor);
    }

    @PatchMapping("/{id}/subjects")
    public ResponseEntity<Mentor> addSubjectsToMentor(
            @PathVariable Long id,
            @Validated @RequestBody SubjectIdsRequestDTO subjectIdsRequestDTO
    ) {
        return sendOkResponse(mentorService.addSubjectsToMentor(id, subjectIdsRequestDTO.getSubjectIds()));
    }

    @DeleteMapping("/{id}/subjects")
    public ResponseEntity<Mentor> removeSubjectsFromMentor(
            @PathVariable Long id,
            @Validated @RequestBody SubjectIdsRequestDTO subjectIdsRequestDTO
    ) {
        return sendOkResponse(mentorService.removeSubjectsFromMentor(id, subjectIdsRequestDTO.getSubjectIds()));
    }
}