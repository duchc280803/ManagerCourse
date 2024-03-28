package com.example.managercourse.service;

import com.example.managercourse.dto.request.TeacherRequest;
import com.example.managercourse.dto.response.*;

import java.util.List;

public interface TeacherService {

    List<TeacherResponse> showListTeacher(Integer pageNumber, Integer pageSize);

    MessageResponse createTeacher(TeacherRequest teacherRequest);

    MessageResponse updateTeacher(Integer id, TeacherRequest teacherRequest);

    UpdateTeacherResponse showDetailTeacher(Integer id);

    List<TeacherSearchResponse> findByTeacher(Integer pageNumber, Integer pageSize, String teacherName);

}
