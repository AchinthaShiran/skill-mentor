package com.skillmentor.service.controllers.v1;

import com.skillmentor.service.controllers.AbstractController;
import com.skillmentor.service.dtos.user.UserCreateRequestDTO;
import com.skillmentor.service.entities.User;
import com.skillmentor.service.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController extends AbstractController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return sendOkResponse(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return sendOkResponse(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Validated @RequestBody UserCreateRequestDTO userDTO) {
        return sendCreatedResponse(userService.createUser(userDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Validated @RequestBody UserCreateRequestDTO userDTO) {
        return sendOkResponse(userService.updateUser(id, userDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> patchUser(@PathVariable Long id, @RequestBody UserCreateRequestDTO userDTO) {
        return sendOkResponse(userService.patchUser(id, userDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return sendNoContentResponse();
    }
}
