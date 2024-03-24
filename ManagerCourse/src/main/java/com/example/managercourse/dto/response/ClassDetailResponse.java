package com.example.managercourse.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClassDetailResponse {

    private Integer id;

    private String classCode;

    private String className;

    private String required;

    private String courseName;

    private String teacherName;

    private Integer quantityStudent;

    private Integer status;
}
