package com.example.managercourse.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentSearchResponse {

    private String fullName;

    private Boolean gender;

    private LocalDate yearOfBirth;

    private LocalDate startDate;

    private String phoneNumber;

    private String email;

    private String address;

    private String courseName;

}
