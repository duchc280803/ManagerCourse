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
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "subject_code")
    private String subjectCode;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "curriculum_content")
    private String curriculumContent;

    @Column(name = "learning_mode")
    private Integer learningMode;

    private Integer numberOfSessions;

    private Integer classify;

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
    private List<Schedule> scheduleList;

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
    private List<SubjectDetail> subjectDetailList;

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
    private List<StatusSubject> statusSubjectList;

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
    private List<Point> pointList;

}
