package com.skillmentor.service.dtos.subject;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class SubjectCreateRequestDTO {

    @NotBlank(message = "Subject name is required")
    @Size(min = 2, max = 100, message = "Subject name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 300, message = "Description must be between 10 and 300 characters")
    private String description;
}
