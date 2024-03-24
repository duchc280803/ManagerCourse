package com.example.managercourse.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectUpdateResponse {

    private Integer id;

    private String subjectCode;

    private String subjectName;

    private String description;

    private String curriculumContent;

    private Integer learningMode;

    private LocalDate studyTimeStart;

    private LocalDate studyTimeEnd;

    private Integer numberOfLessons;

    private Integer classify;

}
