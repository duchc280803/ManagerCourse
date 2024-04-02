package com.example.managercourse.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MailServerRequest {

    private String host;

    private Integer port;

    private String username;

    private String password;

}
