package com.example.managercourse.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TeacherResponse {

    private Integer id;

    private String codeName;

    private String fullName;

    private Boolean gender;

    private Integer age;

    private LocalDate yearOfBirth;

    private String phoneNumber;

    private String address;

    private String email;

    private Integer status;

    private List<CourseOfClass> courseOfClassList;
}
