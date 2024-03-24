package com.example.managercourse.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "study_point")
public class StudyPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String pointName;

    private Integer weight;

    private String description;

    private Integer status;

    @OneToMany(mappedBy = "studyPoint", fetch = FetchType.LAZY)
    private List<Point> pointList;

    @OneToMany(mappedBy = "studyPoint", fetch = FetchType.LAZY)
    private List<PointDetail> pointDetailList;

}
