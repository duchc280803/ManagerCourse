package com.example.managercourse.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectAddCourseRequest {

    @NotEmpty(message = "Selected Student is required")
    private List<Integer> selectedSubjectIds;
}
