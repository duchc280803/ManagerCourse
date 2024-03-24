package com.example.managercourse.controller;

import com.example.managercourse.dto.request.StudyPointRequest;
import com.example.managercourse.dto.response.MessageResponse;
import com.example.managercourse.dto.response.PointBySubjectResponse;
import com.example.managercourse.dto.response.StudyPointResponse;
import com.example.managercourse.dto.response.SubjectResponse;
import com.example.managercourse.entity.StudyPoint;
import com.example.managercourse.service.impl.StudyPointServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/point/")
public class StudyPointController {

    @Autowired
    private StudyPointServiceImpl studyPointService;

    @GetMapping("study-point")
    public ResponseEntity<List<PointBySubjectResponse>> findBySubject(
    ) {
        return new ResponseEntity<>(studyPointService.findBySubject(), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<MessageResponse> createStudyPoint(@Valid @RequestBody StudyPointRequest studyPointRequest
    ) {
        return new ResponseEntity<>(studyPointService.createStudyPoint(studyPointRequest), HttpStatus.CREATED);
    }

    @GetMapping("detail")
    public ResponseEntity<StudyPointResponse> detail(
            @RequestParam(name = "id") Integer id
    ) {
        return new ResponseEntity<>(studyPointService.detail(id), HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<MessageResponse> updateStudyPoint(
            @RequestParam(name = "idStudyPoint") Integer idStudyPoint,
            @Valid @RequestBody StudyPointRequest studyPointRequest
    ) {
        return new ResponseEntity<>(studyPointService.updateStudyPoint(idStudyPoint, studyPointRequest), HttpStatus.CREATED);
    }

}
