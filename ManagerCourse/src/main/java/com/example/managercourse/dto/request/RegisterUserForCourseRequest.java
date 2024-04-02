package com.example.managercourse.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserForCourseRequest {

    private String fullName;

    private String phoneNumber;

    private String email;
}
