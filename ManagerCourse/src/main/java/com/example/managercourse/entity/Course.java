package com.example.managercourse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "course_code")
    private String courseCode;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_price")
    private BigDecimal coursePrice;

    private String description;

    private String scheduled;

    private Integer status;

    @Column(name = "create_at")
    private LocalDate createAt;

    @Column(name = "update_at")
    private LocalDate updateAt;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<CourseDetail> courseDetailList;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<SubjectDetail> subjectDetailList;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Class> classList;
}
