package com.example.managercourse.controller;

import com.example.managercourse.dto.request.CourseRequest;
import com.example.managercourse.dto.request.SubjectAddCourseRequest;
import com.example.managercourse.dto.response.*;
import com.example.managercourse.entity.Course;
import com.example.managercourse.service.impl.CourseServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/course/")
public class CourseController {

    private final CourseServiceImpl courseService;

    @Autowired
    public CourseController(CourseServiceImpl courseService) {
        this.courseService = courseService;
    }

    @GetMapping("show")
    public ResponseEntity<List<CourseResponse>> showCourse(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        return new ResponseEntity<>(courseService.showCourse(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("course-name")
    public ResponseEntity<List<CourseOfTeacherResponse>> findByCourseName() {
        return new ResponseEntity<>(courseService.findByCourseName(), HttpStatus.OK);
    }

    @GetMapping("fill-teacher")
    public ResponseEntity<List<TeacherOfClassResponse>> fillTeacher() {
        return new ResponseEntity<>(courseService.fillTeacher(), HttpStatus.OK);
    }

    @GetMapping("fill-teacher-for-course")
    public ResponseEntity<List<TeacherOfClassResponse>> fillTeacherByCourse(
            @RequestParam("courseName") String courseName
    ) {
        return new ResponseEntity<>(courseService.fillTeacherByCourse(courseName), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<MessageResponse> createTeacher(
            @Valid @RequestBody CourseRequest courseRequest
            ) {
        return new ResponseEntity<>(courseService.createCourse(courseRequest), HttpStatus.CREATED);
    }

    @GetMapping("search")
    public ResponseEntity<List<CourseResponse>> searchTeacher(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "nameCourse") String nameCourse
    ) {
        return new ResponseEntity<>(courseService.searchCourse(pageNumber, pageSize, nameCourse), HttpStatus.OK);
    }

    @GetMapping("detail")
    public ResponseEntity<CourseDetailResponse> showDetailCourse(@RequestParam(name = "id") Integer id) {
        return new ResponseEntity<>(courseService.detailCourse(id), HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<MessageResponse> updateTeacher(
            @RequestParam(name = "id") Integer id,
            @Valid @RequestBody CourseRequest courseRequest
    ) {
        return new ResponseEntity<>(courseService.updateCourse(id, courseRequest), HttpStatus.CREATED);
    }

    @GetMapping("course-student")
    public ResponseEntity<List<CourseOfClass>> getListCourseOfStudent(
            Principal principal
    ) {
        return new ResponseEntity<>(courseService.getListCourse(principal.getName()), HttpStatus.OK);
    }

    @PostMapping("add-course")
    public ResponseEntity<MessageResponse> addStudentToClass(
            @RequestParam(name = "id") Integer id,
            @Valid @RequestBody SubjectAddCourseRequest subjectAddCourseRequest
    ) {
        return new ResponseEntity<>(courseService.addSubjectToCourse(subjectAddCourseRequest, id), HttpStatus.CREATED);
    }

    @GetMapping("select-fill-course")
    public ResponseEntity<Course> findById(
            @RequestParam(name = "id") Integer id
    ) {
        return new ResponseEntity<>(courseService.getCourseId(id), HttpStatus.OK);
    }

}
