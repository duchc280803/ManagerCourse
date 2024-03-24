package com.example.managercourse.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDetailResponse {

    private Integer id;

    private Integer idClass;

    private Integer idSubject;

    private Integer idClassRoom;

    private Integer day;

    private LocalTime timeStart;

    private LocalTime timeEnd;

}
