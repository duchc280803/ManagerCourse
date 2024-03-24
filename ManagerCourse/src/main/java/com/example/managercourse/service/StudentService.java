package com.example.managercourse.service;

import com.example.managercourse.dto.request.StudentRequest;
import com.example.managercourse.dto.response.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentService {

    List<StudentResponse> showListStudent(Integer pageNumber, Integer pageSize);

    MessageResponse createStudent(StudentRequest studentRequest);

    List<StudentSearchResponse> searchStudent(Integer pageNumber, Integer pageSize, String nameStudent);

    List<StudentCbResponse> comboboxStudent();

    StudentResponse studentDetail(Integer id);

    MessageResponse updateStudent(Integer id, StudentRequest studentRequest);

    List<StudentCheckBoxResponse> showListStudent(String courseName);

    List<CourseOfStudent> getListCourseOfStudent(String name, String courseName, Integer pageNumber, Integer pageSize);

}
