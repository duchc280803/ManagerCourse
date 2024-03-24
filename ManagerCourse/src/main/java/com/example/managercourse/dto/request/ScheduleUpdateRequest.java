package com.example.managercourse.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleUpdateRequest {

    @NotNull(message = "Class room is not required")
    private Integer idClassRoom;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Date learn of birth is required")
    private LocalDate dateLearn;

    @NotNull(message = "Start time is required")
    private LocalTime timeStart;

    @NotNull(message = "End time is required")
    private LocalTime timeEnd;
}
