package com.example.managercourse.controller;

import com.example.managercourse.dto.request.ClassRequest;
import com.example.managercourse.dto.request.StudentAddClassRequest;
import com.example.managercourse.dto.response.*;
import com.example.managercourse.entity.Class;
import com.example.managercourse.service.impl.ClassServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/class/")
public class ClassController {

    @Autowired
    private ClassServiceImpl classService;

    @GetMapping("show")
    public ResponseEntity<List<ClassResponse>> findAllClass(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        return new ResponseEntity<>(classService.getClassResponseList(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("search")
    public ResponseEntity<List<ClassResponse>> searchClass(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "className") String className
    ) {
        return new ResponseEntity<>(classService.searchClass(pageNumber, pageSize, className), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<MessageResponse> createClass(@Valid @RequestBody ClassRequest classRequest) {
        return new ResponseEntity<>(classService.createClass(classRequest), HttpStatus.CREATED);
    }

    @GetMapping("student-information")
    public ResponseEntity<List<UserInformationResponse>> listStudentInformation(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(name = "id") Integer id
    ) {
        return new ResponseEntity<>(classService.listStudentInformation(pageNumber, pageSize, id), HttpStatus.OK);
    }

    @GetMapping("teacher-information")
    public ResponseEntity<UserInformationResponse> teacherInformation(
            @RequestParam(name = "id") Integer id
    ) {
        return new ResponseEntity<>(classService.teacherInformation(id), HttpStatus.OK);
    }

    @GetMapping("detail")
    public ResponseEntity<ClassDetailResponse> classDetail(
            @RequestParam(name = "id") Integer id
    ) {
        return new ResponseEntity<>(classService.classDetail(id), HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<MessageResponse> updateClass(
            @RequestParam(name = "id") Integer id,
            @Valid @RequestBody ClassRequest classRequest
    ) {
        return new ResponseEntity<>(classService.updateClass(id, classRequest), HttpStatus.OK);
    }

    @PutMapping("add-student")
    public ResponseEntity<MessageResponse> addStudentToClass(
            @RequestParam(name = "id") Integer id,
            @Valid @RequestBody StudentAddClassRequest studentAddClassRequest
    ) {
        return new ResponseEntity<>(classService.addStudentToClass(studentAddClassRequest, id), HttpStatus.OK);
    }

    @GetMapping("findAllClassName")
    public ResponseEntity<List<ClassResponse>> findAllClassName(
            Principal principal
    ) {
        if (principal == null || principal.getName().equals("admin")) {
            return new ResponseEntity<>(classService.findAllClassName(), HttpStatus.OK);
        }
        return new ResponseEntity<>(classService.findAllClassNameForTeacher(principal.getName()), HttpStatus.OK);
    }

    @GetMapping("findAllClassNameOfStudent")
    public ResponseEntity<List<ClassResponse>> findAllClassNameOfStudent(
            Principal principal
    ) {
        return new ResponseEntity<>(classService.findAllClassNameOfStudent(principal.getName()), HttpStatus.OK);
    }

    @GetMapping("select-student-of-teacher")
    public ResponseEntity<List<StudentOfTeacherResponse>> selectStudentOfTeacher(
            @RequestParam("className") String className
    ) {
        return new ResponseEntity<>(classService.selectStudentOfTeacher(className), HttpStatus.OK);
    }

}
