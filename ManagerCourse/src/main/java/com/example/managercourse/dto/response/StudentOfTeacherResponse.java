package com.example.managercourse.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentOfTeacherResponse {

    private String fullName;

    private String phoneNumber;

    private String email;

    private Boolean gender;

    private String address;
}
