package com.skillmentor.service.controllers.v1;

import com.skillmentor.service.controllers.AbstractController;
import com.skillmentor.service.dtos.MentorDTO;
import com.skillmentor.service.entities.Mentor;
import com.skillmentor.service.services.MentorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
    public ResponseEntity<List<Mentor>> getAllMentors(){
        return sendOkResponse(mentorService.getAllMentors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mentor> getMentorById(@PathVariable int id){
        return sendOkResponse(mentorService.getMentorById(id));
    }

    @PostMapping
    public ResponseEntity<Mentor> createMentor(@Validated @RequestBody MentorDTO mentorDTO){
        // Mapping using Model Mapper
        Mentor mentor = modelMapper.map(mentorDTO, Mentor.class);
        Mentor createdMentor = mentorService.createMentor(mentor);
        return sendCreatedResponse(createdMentor);
    }
}