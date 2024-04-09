package com.example.managercourse.dto.response;

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

    private String image;

    private List<TeacherOfClassResponse> teacherOfClassResponses;
}
