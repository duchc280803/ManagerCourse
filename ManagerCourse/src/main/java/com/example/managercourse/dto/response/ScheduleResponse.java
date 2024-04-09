package com.example.managercourse.dto.response;

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
public class ScheduleResponse {

    private Integer id;

    private String subjectName;

    private String classRoomName;

    private Integer floor;

    private LocalDate dateLean;

    private LocalTime timeStart;

    private LocalTime timeEnd;

}
