package com.example.managercourse.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

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

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "StudyTimeStart of birth is required")
    private LocalDate studyTimeStart;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "StudyTimeEnd of birth is required")
    private LocalDate studyTimeEnd;

    @NotNull(message = "Classify is required")
    private Integer classify;

}
