package com.skillmentor.service.services.impl;

import com.skillmentor.service.dtos.MentorDTO;
import com.skillmentor.service.dtos.user.UserCreateRequestDTO;
import com.skillmentor.service.entities.Mentor;
import com.skillmentor.service.entities.Subject;
import com.skillmentor.service.entities.User;
import com.skillmentor.service.entities.UserType;
import com.skillmentor.service.exceptions.SkillMentorException;
import com.skillmentor.service.respositories.MentorRepository;
import com.skillmentor.service.respositories.SubjectRepository;
import com.skillmentor.service.services.MentorService;
import com.skillmentor.service.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MentorServiceImpl implements MentorService {

    private final MentorRepository mentorRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Override
    public Page<Mentor> getAllMentors(Pageable pageable) {
        try {
            // using pagination
            return mentorRepository.findAll(pageable);
        } catch (Exception exception) {
            log.error("Failed to get mentors", exception);
            throw new SkillMentorException("Failed to get mentors", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Mentor getMentorById(Long id) {
        try {
            return mentorRepository.findById(id).orElseThrow(
                    () -> new SkillMentorException("Mentor Not Found", HttpStatus.NOT_FOUND)
            );
        } catch (SkillMentorException skillMentorException) {
            log.error("Mentor Not found by id {}", id, skillMentorException);
            throw skillMentorException;
        } catch (Exception exception) {
            log.error("Failed to get mentor by id : {}", id, exception);
            throw new SkillMentorException("Failed to get mentor by id", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Mentor createMentor(MentorDTO mentorDTO) {
        try {
            // Create user first
            UserCreateRequestDTO userDTO = modelMapper.map(mentorDTO, UserCreateRequestDTO.class);
            userDTO.setUserType(UserType.MENTOR);

            User user = userService.createUser(userDTO);

            // Create mentor with the user
            Mentor mentor = modelMapper.map(mentorDTO, Mentor.class);
            mentor.setUser(user);

            return mentorRepository.save(mentor);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            log.error("Data Integrity in inserting a new mentor", dataIntegrityViolationException);
            throw new SkillMentorException("User with same email exists", HttpStatus.CONFLICT);
        } catch (Exception exception) {
            log.error("Error in inserting a new mentor", exception);
            throw new SkillMentorException("Failed to create new mentor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Mentor addSubjectsToMentor(Long mentorId, List<Long> subjectIds) {
        try {
            // TODO: This action could be optimized
            // Verify mentor exists
            getMentorById(mentorId);
            // Insert all subjects in single transaction
            mentorRepository.addSubjectsToMentorBatch(mentorId, subjectIds);
            // Return updated mentor
            return getMentorById(mentorId);
        } catch (SkillMentorException skillMentorException) {
            // This SkillMentorException is thrown from the getMentorById method (line 87)
            log.error("Failed to add mentor subjects for mentor with id {}, because failed to get mentor",
                    mentorId, skillMentorException);
            throw skillMentorException;
        } catch (Exception e) {
            log.error("Failed to add mentor subjects for mentor with id {}", mentorId);
            throw new SkillMentorException("Failed to add subject to mentor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Mentor removeSubjectsFromMentor(Long mentorId, List<Long> subjectIds) {
        try {
            // TODO: This action could be optimized
            // Verify mentor exists
            getMentorById(mentorId);
            // Delete subjects directly from join table
            mentorRepository.removeSubjectsFromMentor(mentorId, subjectIds);
            // Return updated mentor
            return getMentorById(mentorId);
        } catch (SkillMentorException skillMentorException) {
            // This SkillMentorException is thrown from the getMentorById method (line 87)
            log.error("Failed to remove mentor subjects for mentor with id {}, because failed to get mentor",
                    mentorId, skillMentorException);
            throw skillMentorException;
        } catch (Exception e) {
            log.error("Failed to remove mentor subjects for mentor with id {}", mentorId);
            throw new SkillMentorException("Failed to remove subject to mentor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
