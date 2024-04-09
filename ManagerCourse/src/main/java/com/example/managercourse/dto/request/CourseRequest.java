package com.example.managercourse.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class CourseRequest {

    @NotBlank(message = "Course code is required")
    private String courseCode;

    @NotBlank(message = "Course name is required")
    private String courseName;

    @DecimalMin(value = "0.0", inclusive = false, message = "Course price must be greater than 0")
    @NotNull(message = "Course price is required")
    private BigDecimal coursePrice;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Status is required")
    private Integer status;

    @NotBlank(message = "Scheduled is required")
    private String scheduled;

    @NotBlank(message = "Image is required")
    private String image;

    @NotEmpty(message = "At least one teacher name is required")
    private List<String> nameTeacher;

}
