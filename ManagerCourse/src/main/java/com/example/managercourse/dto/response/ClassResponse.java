package com.example.managercourse.dto.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ClassResponse {

    private Integer id;

    private String classCode;

    private String className;

    private Integer quantityStudent;

    private String subjectName;

    private String teacherName;

    private Integer status;


    public ClassResponse(Integer id, String className) {
        this.id = id;
        this.className = className;
    }
}
