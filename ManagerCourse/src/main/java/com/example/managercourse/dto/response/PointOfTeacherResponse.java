package com.example.managercourse.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PointOfTeacherResponse {

    private String fullName;

    private List<String> pointName;

    private List<Integer> weight;

    private List<BigDecimal> point;

}
