package com.skillmentor.service.services;

import com.skillmentor.service.entities.Mentor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MentorService {

    List<Mentor> getAllMentors();
    Mentor getMentorById(int id);
    Mentor createMentor(Mentor mentor);

}
