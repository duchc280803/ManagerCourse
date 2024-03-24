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
public class SubjectResponse {

    private Integer id;

    private String subjectCode;

    private String subjectName;

    private String curriculumContent;

    private Integer learningMode;

    private LocalDate studyTimeStart;

    private LocalDate studyTimeEnd;

    private Integer classify;

    public SubjectResponse(Integer id, String subjectName) {
        this.id = id;
        this.subjectName = subjectName;
    }
}