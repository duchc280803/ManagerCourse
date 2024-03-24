package com.example.managercourse.dto.response;

import jakarta.persistence.Column;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CourseResponse {

    private Integer id;

    private String courseCode;

    private String courseName;

    private Integer courseTime;

    private BigDecimal coursePrice;

    private Integer quantitySubject;

    private String scheduled;

    private String description;

    private Integer status;

    private List<TeacherOfClassResponse> teacherOfClassResponses;
}
