package com.skillmentor.service.dtos;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class MentorDTO {

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

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must not exceed 100 characters")
    private String title;

    @NotBlank(message = "Profession is required")
    @Size(max = 100, message = "Profession must not exceed 100 characters")
    private String profession;

    @NotBlank(message = "Company is required")
    @Size(max = 100, message = "Company name must not exceed 100 characters")
    private String company;

    @Min(value = 0, message = "Experience years cannot be negative")
    @Max(value = 100, message = "Experience years must be realistic")
    private int experienceYears;

    @Size(min = 50, max = 1000, message = "Bio must be between 50 and 1000 characters")
    private String bio;
}
