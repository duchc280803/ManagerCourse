package com.example.managercourse.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInformationResponse {

    private String fullName;

    private Boolean gender;

    private String phoneNumber;

    private String email;

}
