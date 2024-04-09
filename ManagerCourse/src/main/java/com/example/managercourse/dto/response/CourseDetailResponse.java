package com.example.managercourse.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CourseDetailResponse {

    private Integer id;

    private String courseCode;

    private String courseName;

    private BigDecimal coursePrice;

    private Integer quantitySubject;

    private String description;

    private String scheduled;

    private Integer status;

    private String image;

    private List<String> nameTeacher;
}
