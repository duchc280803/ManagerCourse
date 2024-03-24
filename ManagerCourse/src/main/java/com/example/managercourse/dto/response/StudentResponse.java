package com.example.managercourse.dto.response;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class StudentResponse {

    private Integer id;

    private String codeName;

    private String fullName;

    private Boolean gender;

    private LocalDate yearOfBirth;

    private LocalDate startDate;

    private String phoneNumber;

    private String email;

    private String address;

    private String courseName;

    private Integer status;
}
