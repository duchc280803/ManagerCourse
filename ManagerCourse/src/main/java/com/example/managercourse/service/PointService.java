package com.example.managercourse.service;

import com.example.managercourse.dto.request.PostPointRequest;
import com.example.managercourse.dto.response.MessageResponse;
import com.example.managercourse.dto.response.PointBySubjectClassResponse;
import com.example.managercourse.dto.response.PointBySubjectResponse;

import java.util.List;

public interface PointService {

    List<PointBySubjectResponse> selectPointStudent(String username, String course);

    List<PointBySubjectClassResponse> selectPointTeacher(String className, String subjectName);

    MessageResponse updatePoint(Integer idPoint,PostPointRequest postPointRequest);

    MessageResponse createPoint(Integer idSubject, Integer idUser, PostPointRequest postPointRequest);

    List<PointBySubjectResponse> selectPointForUser(Integer id);

}
