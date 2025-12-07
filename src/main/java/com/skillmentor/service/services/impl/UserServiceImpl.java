package com.skillmentor.service.services.impl;

import com.skillmentor.service.dtos.user.UserCreateRequestDTO;
import com.skillmentor.service.entities.User;
import com.skillmentor.service.exceptions.SkillMentorException;
import com.skillmentor.service.respositories.UserRepository;
import com.skillmentor.service.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Data
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    // TODO: Exception handling
    // TODO: Error Logging
    // TODO: Pagination

    @Override
    public List<User> getAllUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception exception) {
            log.error("Failed to get all users", exception);
            throw new SkillMentorException("Error retrieving all users", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public User getUserById(Long id) {
        try {
            return userRepository.findById(id)
                    .orElseThrow(
                            () -> new SkillMentorException(
                                    "User Not found",
                                    HttpStatus.NOT_FOUND)
                    );
        } catch (SkillMentorException skillMentorException) {
            log.error("User not found with id: {}", id, skillMentorException);
            throw skillMentorException;
        } catch (Exception exception) {
            log.error("Failed to get user with id: {}", id, exception);
            throw new SkillMentorException("Error retrieving user with id " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public User createUser(UserCreateRequestDTO userDTO) {
        try {
            User user = modelMapper.map(userDTO, User.class);
            return userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Error creating user: " + e.getMessage(), e);
        }
    }

    @Override
    public User updateUser(Long id, UserCreateRequestDTO userDTO) {
        try {
            User user = getUserById(id);
            modelMapper.map(userDTO, user);
            return userRepository.save(user);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error updating user with id " + id + ": " + e.getMessage(), e);
        }
    }

    @Override
    public User patchUser(Long id, UserCreateRequestDTO userDTO) {
        try {
            User user = getUserById(id);
            modelMapper.map(userDTO, user);
            return userRepository.save(user);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error patching user with id " + id + ": " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting user with id " + id + ": " + e.getMessage(), e);
        }
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            return userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving user with email " + email + ": " + e.getMessage(), e);
        }
    }
}
