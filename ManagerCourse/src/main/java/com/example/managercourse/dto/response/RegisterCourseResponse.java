package com.example.managercourse.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class RegisterCourseResponse {

    private Integer id;

    private String courseName;

    private String courseCode;

    private Integer quantitySubject;

    private BigDecimal coursePrice;

    private String description;

    private String scheduled;

}
