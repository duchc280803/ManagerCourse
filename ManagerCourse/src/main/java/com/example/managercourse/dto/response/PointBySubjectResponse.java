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

    private String pointName;

    private Integer weight;

    private String description;

    private Integer status;
}
