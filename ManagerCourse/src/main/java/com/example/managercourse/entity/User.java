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
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    @Column(name = "code_name")
    private String codeName;

    @Column(name = "full_name")
    private String fullName;

    private Boolean gender;

    @Column(name = "year_of_birth")
    private LocalDate yearOfBirth;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String address;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    private String email;

    @Column(name = "status")
    private Integer status;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<CourseDetail> courseDetailList;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ClassDetail> classDetailList;

    @OneToMany(mappedBy = "userTeacher", fetch = FetchType.LAZY)
    private List<Class> classList;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Point> pointList;

}
