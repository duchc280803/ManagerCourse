package com.example.managercourse.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private Integer numberOfSessions;

    private Integer classify;

    public SubjectResponse(Integer id, String subjectName) {
        this.id = id;
        this.subjectName = subjectName;
    }
}
