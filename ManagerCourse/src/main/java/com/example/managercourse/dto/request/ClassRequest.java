package com.example.managercourse.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClassRequest {

    @NotBlank(message = "Class code is required")
    private String classCode;

    @NotBlank(message = "Class name is required")
    private String className;

    @NotBlank(message = "Required field is required")
    private String required;

    @NotBlank(message = "Course name is required")
    private String courseName;

    @NotBlank(message = "Teacher name is required")
    private String teacherName;

    @NotNull(message = "Quantity of students is required")
    @Min(value = 1, message = "Quantity of students must be at least 1")
    @Max(value = 50, message = "The maximum number of students must be 50")
    private Integer quantityStudent;

    private Integer status;

}
