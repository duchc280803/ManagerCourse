package com.example.managercourse.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContactRequest {

    private Integer id;

    private String fullName;

    private String email;

    private String phoneNumber;

    private String message;
}
