package com.example.managercourse.controller;

import com.example.managercourse.dto.request.SubjectCreateRequest;
import com.example.managercourse.dto.request.UpdateSubjectRequest;
import com.example.managercourse.dto.response.MessageResponse;
import com.example.managercourse.dto.response.SubjectResponse;
import com.example.managercourse.dto.response.SubjectUpdateResponse;
import com.example.managercourse.service.impl.SubjectServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subject/")
public class SubjectController {

    private final SubjectServiceImpl subjectService;

    @Autowired
    public SubjectController(SubjectServiceImpl subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("show")
    public ResponseEntity<List<SubjectResponse>> getListSubjectName(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "8") Integer pageSize
    ) {
        return new ResponseEntity<>(subjectService.getListSubjectName(pageNumber, pageSize), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<MessageResponse> createSubject(
            @Valid @RequestBody SubjectCreateRequest subjectCreateRequest
    ) {
        return new ResponseEntity<>(subjectService.createSubject(subjectCreateRequest), HttpStatus.OK);
    }

    @GetMapping("detail")
    public ResponseEntity<SubjectUpdateResponse> subjectDetail(
            @RequestParam(name = "id") Integer id
    ) {
        return new ResponseEntity<>(subjectService.subjectDetail(id), HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<MessageResponse> updateSubject(
            @PathVariable(name = "id") Integer id,
            @Valid @RequestBody UpdateSubjectRequest updateSubjectRequest
    ) {
        return new ResponseEntity<>(subjectService.updateSubject(id, updateSubjectRequest), HttpStatus.OK);
    }

    @GetMapping("search")
    public ResponseEntity<List<SubjectResponse>> searchListSubject(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(name = "subjectName") String subjectName
    ) {
        return new ResponseEntity<>(subjectService.searchListSubject(pageNumber, pageSize, subjectName), HttpStatus.OK);
    }

    @GetMapping("subject-by-course")
    public ResponseEntity<List<SubjectResponse>> getListSubjectCbb(
            @RequestParam(name = "courseName") String courseName
    ) {
        return new ResponseEntity<>(subjectService.getListSubjectByCourse(courseName), HttpStatus.OK);
    }

    @GetMapping("subject-by-class")
    public ResponseEntity<List<SubjectResponse>> getListSubjectByClass(
            @RequestParam(name = "id") Integer id
    ) {
        return new ResponseEntity<>(subjectService.getListSubjectByClass(id), HttpStatus.OK);
    }

    @GetMapping("subject-name")
    public ResponseEntity<List<SubjectResponse>> getListSubjectName(
            @RequestParam(name = "id") Integer id
    ) {
        return new ResponseEntity<>(subjectService.getListSubjectName(id), HttpStatus.OK);
    }

    @GetMapping("subject-course")
    public ResponseEntity<List<SubjectResponse>> getListSubjectByCourse(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "8") Integer pageSize,
            @RequestParam(name = "id") Integer id,
            @RequestParam(name = "classify") Integer classify
    ) {
        return new ResponseEntity<>(subjectService.getListSubjectByCourse(pageNumber, pageSize, id, classify), HttpStatus.OK);
    }

    @GetMapping("subject-add-course")
    public ResponseEntity<List<SubjectResponse>> getListSubjectAddCourse(
            @RequestParam(name = "id") Integer id
    ) {
        return new ResponseEntity<>(subjectService.getListSubjectAddCourse(id), HttpStatus.OK);
    }

    @GetMapping("subject-by-class-schedule")
    public ResponseEntity<List<SubjectResponse>> getListSubjectForClassSubjectSchedule(
            @RequestParam(name = "id") Integer id
    ) {
        return new ResponseEntity<>(subjectService.getListSubjectForClassSubjectSchedule(id), HttpStatus.OK);
    }
}
