package com.example.managercourse.controller;

import com.example.managercourse.dto.request.TeacherRequest;
import com.example.managercourse.dto.response.MessageResponse;
import com.example.managercourse.dto.response.TeacherResponse;
import com.example.managercourse.dto.response.TeacherSearchResponse;
import com.example.managercourse.dto.response.UpdateTeacherResponse;
import com.example.managercourse.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teacher/")
public class TeacherController {

    @Autowired
    private TeacherServiceImpl teacherService;

    @GetMapping("show")
    public ResponseEntity<List<TeacherResponse>> showListTeacher(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        return new ResponseEntity<>(teacherService.showListTeacher(pageNumber, pageSize), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<MessageResponse> createTeacher(
            @RequestBody TeacherRequest teacherRequest
    ) {
        return new ResponseEntity<>(teacherService.createTeacher(teacherRequest), HttpStatus.CREATED);
    }

    @GetMapping("detail")
    public ResponseEntity<UpdateTeacherResponse> showDetailTeacher(@RequestParam(name = "id") Integer id) {
        return new ResponseEntity<>(teacherService.showDetailTeacher(id), HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<MessageResponse> updateTeacher(
            @RequestParam(name = "id") Integer id,
            @RequestBody TeacherRequest teacherRequest
    ) {
        return new ResponseEntity<>(teacherService.updateTeacher(id, teacherRequest), HttpStatus.OK);
    }

    @GetMapping("search")
    public ResponseEntity<List<TeacherSearchResponse>> searcherTeacher(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "teacherName") String teacherName
    ) {
        return new ResponseEntity<>(teacherService.findByTeacher(pageNumber, pageSize, teacherName), HttpStatus.OK);
    }

}
