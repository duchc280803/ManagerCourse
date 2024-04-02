package com.example.managercourse.controller;

import com.example.managercourse.dto.request.RegisterUserForCourseRequest;
import com.example.managercourse.dto.request.StudentRequest;
import com.example.managercourse.dto.response.*;
import com.example.managercourse.service.impl.StudentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/student/")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @GetMapping("show")
    public ResponseEntity<List<StudentResponse>> showListStudent(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        return new ResponseEntity<>(studentService.showListStudent(pageNumber, pageSize), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<MessageResponse> createStudent(
            @Valid @RequestBody StudentRequest studentRequest
    ) {
        return new ResponseEntity<>(studentService.createStudent(studentRequest), HttpStatus.CREATED);
    }

    @PostMapping("create-for-user")
    public ResponseEntity<MessageResponse> createStudentForCourse(
            @Valid @RequestBody RegisterUserForCourseRequest registerUserForCourseRequest
    ) {
        return new ResponseEntity<>(studentService.createStudentForCourse(registerUserForCourseRequest), HttpStatus.CREATED);
    }

    @GetMapping("search")
    public ResponseEntity<List<StudentSearchResponse>> showListStudent(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "studentName") String studentName
    ) {
        return new ResponseEntity<>(studentService.searchStudent(pageNumber, pageSize, studentName), HttpStatus.OK);
    }

    @GetMapping("student-combobox")
    public ResponseEntity<List<StudentCbResponse>> comboboxStudent() {
        return new ResponseEntity<>(studentService.comboboxStudent(), HttpStatus.OK);
    }

    @GetMapping("detail")
    public ResponseEntity<StudentResponse> studentDetail(
            @RequestParam(name = "id") Integer id
    ) {
        return new ResponseEntity<>(studentService.studentDetail(id), HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<MessageResponse> updateStudent(
            @RequestParam(name = "id") Integer id,
            @Valid @RequestBody StudentRequest studentRequest
    ) {
        return new ResponseEntity<>(studentService.updateStudent(id, studentRequest), HttpStatus.CREATED);
    }

    @GetMapping("show-cb-student")
    public ResponseEntity<List<StudentCheckBoxResponse>> showListStudent(
            @RequestParam(name = "courseName") String courseName
    ) {
        return new ResponseEntity<>(studentService.showListStudent(courseName), HttpStatus.OK);
    }

    @GetMapping("course-of-student")
    public ResponseEntity<List<CourseOfStudent>> getListCourseOfStudent(
            Principal principal,
            @RequestParam(name = "courseName") String courseName,
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        return new ResponseEntity<>(studentService.getListCourseOfStudent(principal.getName(), courseName, pageNumber, pageSize), HttpStatus.OK);
    }

}
