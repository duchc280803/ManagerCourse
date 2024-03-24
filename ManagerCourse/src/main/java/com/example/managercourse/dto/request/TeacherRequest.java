package com.example.managercourse.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRequest {

    private String fullName;

    private Boolean gender;

    private LocalDate dateOfBirth;

    private String phoneNumber;

    private String email;

    private String address;

    List<String> inputValue;
}
