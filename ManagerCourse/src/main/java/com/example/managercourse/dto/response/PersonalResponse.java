package com.example.managercourse.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonalResponse {

    private String username;

    private String fullName;

    private String email;

    private String phoneNumber;

    private String address;

    private LocalDate dateOfBirth;

    private Boolean gender;

    private Integer age;
}
