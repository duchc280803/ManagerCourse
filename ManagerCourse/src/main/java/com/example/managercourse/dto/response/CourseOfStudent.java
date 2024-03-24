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
public class CourseOfStudent {

    private String className;

    private String subjectCode;

    private String subjectName;

    private String teacherName;

    private Integer learningMode;

    private LocalDate startTime;

    private LocalDate endTime;

    private Integer status;
}
