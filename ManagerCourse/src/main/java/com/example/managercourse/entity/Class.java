package com.example.managercourse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "class")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "class_code")
    private String classCode;

    @Column(name = "class_name")
    private String className;

    @Column(name = "quantity_student")
    private Integer quantityStudent;

    private String requirements;

    private Integer status;

    @Column(name = "create_at")
    private LocalDate createAt;

    @Column(name = "update_at")
    private LocalDate updateAt;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "user_teacher_id")
    private User userTeacher;

    @OneToMany(mappedBy = "aClass", fetch = FetchType.LAZY)
    private List<ClassDetail> classDetailList;

    @OneToMany(mappedBy = "aClass", fetch = FetchType.LAZY)
    private List<Schedule> scheduleList;
}
