package com.example.managercourse.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudyPointResponse {

    private Integer id;

    private String pointName;

    private Integer weight;

    private String description;

    private Integer status;

}
