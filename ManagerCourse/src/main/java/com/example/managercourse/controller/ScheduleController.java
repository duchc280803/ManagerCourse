package com.example.managercourse.controller;

import com.example.managercourse.dto.request.ScheduleRequest;
import com.example.managercourse.dto.request.ScheduleUpdateRequest;
import com.example.managercourse.dto.response.MessageResponse;
import com.example.managercourse.dto.response.ScheduleDetailResponse;
import com.example.managercourse.dto.response.ScheduleResponse;
import com.example.managercourse.service.impl.ScheduleServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/schedule/")
public class ScheduleController {

    private final ScheduleServiceImpl scheduleService;
    
    @Autowired
    public ScheduleController(ScheduleServiceImpl scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("show")
    public ResponseEntity<List<ScheduleResponse>> findAllSchedule(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(name = "id", required = false) Integer id,
            @RequestParam(name = "idSubject", required = false) Integer idSubject,
            Principal principal) {
        String username = principal.getName();
        return new ResponseEntity<>(scheduleService.findAllSchedule(pageNumber, pageSize, id, idSubject, username), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<MessageResponse> createSchedule(@Valid @RequestBody ScheduleRequest scheduleRequest) {
        return new ResponseEntity<>(scheduleService.createSchedule(scheduleRequest), HttpStatus.CREATED);
    }

    @GetMapping("detail")
    public ResponseEntity<ScheduleDetailResponse> detail(
            @RequestParam(name = "id") Integer id
    ) {
        return new ResponseEntity<>(scheduleService.detail(id), HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<MessageResponse> updateSchedule(
            @RequestParam(name = "id") Integer id,
            @Valid @RequestBody ScheduleUpdateRequest scheduleUpdateRequest) {
        return new ResponseEntity<>(scheduleService.updateSchedule(id, scheduleUpdateRequest), HttpStatus.OK);
    }

    @GetMapping("schedule-by-course")
    public ResponseEntity<List<ScheduleResponse>> findAllScheduleByCourse(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(name = "id") Integer id,
            Principal principal
    ) {
        return new ResponseEntity<>(scheduleService.findAllScheduleByCourse(pageNumber, pageSize, id, principal.getName()), HttpStatus.OK);
    }

    @GetMapping("check-time")
    public ResponseEntity<Boolean> findAllScheduleByCourse(
            @RequestParam(name = "startTime") LocalTime startTime,
            @RequestParam(name = "endTime") LocalTime endTime,
            @RequestParam(name = "day") Integer day,
            @RequestParam(name = "id") Integer id
    ) {
        return new ResponseEntity<>(scheduleService.checkTimeSchedule(startTime, endTime, day, id), HttpStatus.OK);
    }

}
