package com.example.managercourse.service;

import com.example.managercourse.dto.request.StudyPointRequest;
import com.example.managercourse.dto.response.MessageResponse;
import com.example.managercourse.dto.response.PointBySubjectResponse;
import com.example.managercourse.dto.response.StudyPointResponse;
import com.example.managercourse.entity.StudyPoint;

import java.util.List;

public interface StudyPointService {

    List<PointBySubjectResponse> findBySubject();

    MessageResponse createStudyPoint(StudyPointRequest studyPointRequest);

    MessageResponse updateStudyPoint(Integer idStudyPoint, StudyPointRequest studyPointRequest);

    StudyPointResponse detail(Integer id);
}
