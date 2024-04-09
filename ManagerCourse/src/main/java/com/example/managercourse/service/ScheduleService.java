package com.example.managercourse.service;

import com.example.managercourse.dto.request.ScheduleRequest;
import com.example.managercourse.dto.request.ScheduleUpdateRequest;
import com.example.managercourse.dto.response.MessageResponse;
import com.example.managercourse.dto.response.ScheduleDetailResponse;
import com.example.managercourse.dto.response.ScheduleResponse;

import java.time.LocalTime;
import java.util.List;

public interface ScheduleService {

    List<ScheduleResponse> findAllSchedule(Integer pageNumber, Integer pageSize, Integer id,  Integer idSubject, String username);

    MessageResponse createSchedule(ScheduleRequest scheduleRequest);

    ScheduleDetailResponse detail(Integer id);

    MessageResponse updateSchedule(Integer id, ScheduleUpdateRequest scheduleUpdateRequest);

    List<ScheduleResponse> findAllScheduleByCourse(Integer pageNumber, Integer pageSize, Integer id, String username);

    Boolean checkTimeSchedule(LocalTime timeStart, LocalTime timeEnd, Integer day, Integer id);
}
