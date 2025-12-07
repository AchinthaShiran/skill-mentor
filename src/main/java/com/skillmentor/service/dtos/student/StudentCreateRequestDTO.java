package com.skillmentor.service.dtos.student;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class StudentCreateRequestDTO {

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String email;

    @Pattern(regexp = "^[0-9]{9,10}$", message = "Phone number must be 9-10 digits")
    private String phoneNumber;

    @NotBlank(message = "Learning goals is required")
    @Size(min = 10, max = 1000, message = "Learning goals must be between 10 and 1000 characters")
    private String learningGoals;
}
