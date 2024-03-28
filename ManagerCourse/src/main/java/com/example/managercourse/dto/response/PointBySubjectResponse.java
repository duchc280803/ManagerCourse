package com.example.managercourse.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PointBySubjectResponse {

    private Integer id;

    private String fullName;

    private String subjectName;

    private BigDecimal point;

    public PointBySubjectResponse(String subjectName, BigDecimal point) {
        this.subjectName = subjectName;
        this.point = point;
    }

    public PointBySubjectResponse(String fullName, String subjectName, BigDecimal point) {
        this.fullName = fullName;
        this.subjectName = subjectName;
        this.point = point;
    }
}
