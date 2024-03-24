package com.example.managercourse.service.impl;

import com.example.managercourse.dto.request.StudyPointRequest;
import com.example.managercourse.dto.response.MessageResponse;
import com.example.managercourse.dto.response.PointBySubjectResponse;
import com.example.managercourse.dto.response.StudyPointResponse;
import com.example.managercourse.entity.StudyPoint;
import com.example.managercourse.entity.Subject;
import com.example.managercourse.repository.StudyPointRepository;
import com.example.managercourse.repository.SubjectRepository;
import com.example.managercourse.service.StudyPointService;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudyPointServiceImpl implements StudyPointService {

    @Autowired
    private StudyPointRepository studyPointRepository;

    @Override
    public List<PointBySubjectResponse> findBySubject() {
        return studyPointRepository.findBySubject();
    }

    @Override
    @Transactional
    public MessageResponse createStudyPoint(StudyPointRequest studyPointRequest) {
        StudyPoint studyPoint = StudyPoint
                .builder()
                .pointName(studyPointRequest.getPointName())
                .weight(studyPointRequest.getWeight())
                .description(studyPointRequest.getDescription())
                .status(1)
                .build();
        studyPointRepository.save(studyPoint);
        return MessageResponse.builder().message("Thêm thành công").build();
    }

    @Override
    @Transactional
    public MessageResponse updateStudyPoint(Integer idStudyPoint, StudyPointRequest studyPointRequest) {
        Optional<StudyPoint> studyPointOptional = studyPointRepository.findById(idStudyPoint);

        return studyPointOptional.map(existingStudyPoint -> {
            existingStudyPoint.setPointName(studyPointRequest.getPointName());
            existingStudyPoint.setWeight(studyPointRequest.getWeight());
            existingStudyPoint.setDescription(studyPointRequest.getDescription());
            existingStudyPoint.setStatus(studyPointRequest.getStatus());
            studyPointRepository.save(existingStudyPoint);
            return new MessageResponse("Cập nhật thành công");
        }).orElseGet(() -> new MessageResponse("Không tìm thấy điểm"));
    }

    @Override
    public StudyPointResponse detail(Integer id) {
        return studyPointRepository.findById(id)
                .map(studyPoint -> StudyPointResponse.builder()
                        .id(studyPoint.getId())
                        .pointName(studyPoint.getPointName())
                        .description(studyPoint.getDescription())
                        .weight(studyPoint.getWeight())
                        .status(studyPoint.getStatus())
                        .build())
                .orElse(null);
    }

}
