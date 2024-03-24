package com.example.managercourse.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudyPointRequest {

    @NotBlank(message = "Point name is required")
    private String pointName;

    @NotNull(message = "Weight is required")
    @Min(value = 1, message = "Weight must be a positive integer")
    @Max(value = 100, message = "The largest weight is 100")
    private Integer weight;

    @Size(max = 10, message = "Description cannot exceed 10 characters")
    @NotBlank(message = "Description name is required")
    private String description;

    private Integer status;

}
