package com.example.managercourse.dto.response;

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
public class UpdateTeacherResponse {

    private Integer id;

    private String fullName;

    private Boolean gender;

    private Integer age;

    private LocalDate yearOfBirth;

    private String phoneNumber;

    private String address;

    private String email;

    private List<String> inputValue;
}
