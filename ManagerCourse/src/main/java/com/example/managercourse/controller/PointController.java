package com.example.managercourse.controller;

import com.example.managercourse.dto.request.PostPointRequest;
import com.example.managercourse.dto.response.MessageResponse;
import com.example.managercourse.dto.response.PointBySubjectClassResponse;
import com.example.managercourse.dto.response.PointBySubjectResponse;
import com.example.managercourse.service.impl.PointServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/point/")
public class PointController {

    @Autowired
    private PointServiceImpl studyPointService;

    @GetMapping("select-point-student")
    public ResponseEntity<List<PointBySubjectResponse>> selectPointStudent(
            Principal principal,
            @RequestParam(name = "courseName") String courseName
    ) {
        return new ResponseEntity<>(studyPointService.selectPointStudent(principal.getName(), courseName), HttpStatus.OK);
    }

    @GetMapping("select-point-teacher")
    public ResponseEntity<List<PointBySubjectClassResponse>> selectPointTeacher(
            @RequestParam(name = "className") String className,
            @RequestParam(name = "subjectName") String subjectName

    ) {
        return new ResponseEntity<>(studyPointService.selectPointTeacher(className, subjectName), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<MessageResponse> createPoint(
            @RequestParam(name = "idSubject") Integer idSubject,
            @RequestParam(name = "idUser") Integer idUser,
            @RequestBody PostPointRequest postPointRequest

    ) {
        return new ResponseEntity<>(studyPointService.createPoint(idSubject, idUser, postPointRequest), HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity<MessageResponse> createOrUpdatePoint(
            @RequestParam(name = "idPoint") Integer idPoint,
            @RequestBody PostPointRequest postPointRequest

    ) {
        return new ResponseEntity<>(studyPointService.UpdatePoint(idPoint, postPointRequest), HttpStatus.OK);
    }

    @GetMapping("select-point-for-user")
    public ResponseEntity<List<PointBySubjectResponse>> selectPointForUser(
            @RequestParam(name = "id") Integer id
    ) {
        return new ResponseEntity<>(studyPointService.selectPointForUser(id), HttpStatus.OK);
    }

}
