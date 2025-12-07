package com.skillmentor.service.dtos.student;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class StudentPatchRequestDTO {

    @Size(min = 10, max = 1000, message = "Learning goals must be between 10 and 1000 characters")
    private String learningGoals;

    @Pattern(regexp = "^[0-9]{9,10}$", message = "Phone number must be 9-10 digits")
    private String phoneNumber;
}
