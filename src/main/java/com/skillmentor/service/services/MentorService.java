package com.skillmentor.service.services;

import com.skillmentor.service.dtos.MentorDTO;
import com.skillmentor.service.entities.Mentor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MentorService {

    Page<Mentor> getAllMentors(Pageable pageable);
    Mentor getMentorById(Long id);
    Mentor createMentor(MentorDTO mentorDTO);
    Mentor addSubjectsToMentor(Long mentorId, List<Long> subjectIds);
    Mentor removeSubjectsFromMentor(Long mentorId, List<Long> subjectIds);

}
