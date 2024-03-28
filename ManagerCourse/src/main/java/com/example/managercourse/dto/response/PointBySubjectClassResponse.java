package com.example.managercourse.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PointBySubjectClassResponse {

    private String fullName;

    private BigDecimal point;

    private Integer idUser;

    private Integer idPoint;
}
