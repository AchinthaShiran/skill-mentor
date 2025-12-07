package com.skillmentor.service.services;

import com.skillmentor.service.dtos.user.UserCreateRequestDTO;
import com.skillmentor.service.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User createUser(UserCreateRequestDTO userDTO);
    User updateUser(Long id, UserCreateRequestDTO userDTO);
    User patchUser(Long id, UserCreateRequestDTO userDTO);
    void deleteUser(Long id);
    User getUserByEmail(String email);
}
