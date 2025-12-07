package com.skillmentor.service.dtos.subject;

import lombok.Data;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Data
public class SubjectIdsRequestDTO {

    @NotEmpty(message = "Subject IDs list cannot be empty")
    private List<Long> subjectIds;
}
