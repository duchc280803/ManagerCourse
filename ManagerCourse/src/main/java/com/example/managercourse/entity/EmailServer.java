package com.example.managercourse.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "email_server")
public class EmailServer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String host;

    private Integer port;

    private String username;

    private String password;

    private Boolean auth;

    private Boolean starttls;

    private Integer status;
}
