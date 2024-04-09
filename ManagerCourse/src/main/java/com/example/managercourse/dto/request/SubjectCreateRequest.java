package com.example.managercourse.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectCreateRequest {

    @NotBlank(message = "Subject code is required")
    private String subjectCode;

    @NotBlank(message = "Subject name is required")
    private String subjectName;

    @NotBlank(message = "Curriculum Content name is required")
    private String curriculumContent;

    @NotNull(message = "Learning mode is required")
    private Integer learningMode;

    @NotNull(message = "Classify is required")
    private Integer classify;

    @NotNull(message = "Number of sessions is required")
    private Integer numberOfSessions;

}
