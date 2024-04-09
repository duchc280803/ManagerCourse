package com.example.managercourse.service.impl;

import com.example.managercourse.dto.request.PostPointRequest;
import com.example.managercourse.dto.response.MessageResponse;
import com.example.managercourse.dto.response.PointBySubjectClassResponse;
import com.example.managercourse.dto.response.PointBySubjectResponse;
import com.example.managercourse.entity.Point;
import com.example.managercourse.entity.Subject;
import com.example.managercourse.entity.User;
import com.example.managercourse.exception.NotFoundException;
import com.example.managercourse.repository.PointRepository;
import com.example.managercourse.repository.SubjectRepository;
import com.example.managercourse.repository.UserRepository;
import com.example.managercourse.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PointServiceImpl implements PointService {

    private final PointRepository pointRepository;

    private final UserRepository userRepository;

    private final SubjectRepository subjectRepository;

    @Autowired
    public PointServiceImpl(PointRepository pointRepository,
                            UserRepository userRepository,
                            SubjectRepository subjectRepository) {
        this.pointRepository = pointRepository;
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<PointBySubjectResponse> selectPointStudent(String username, String course) {
        return pointRepository.selectPointStudent(username, course);
    }

    @Override
    public List<PointBySubjectClassResponse> selectPointTeacher(String className, String subjectName) {
        List<Object[]> results = pointRepository.selectPointTeacher(subjectName, className); // Lưu ý thứ tự tham số
        List<PointBySubjectClassResponse> responses = new ArrayList<>();
        for (Object[] result : results) {
            String fullName = (String) result[0];
            BigDecimal point = (BigDecimal) result[1];
            Integer idUser = (Integer) result[2];
            Integer idPoint = (Integer) result[3];
            PointBySubjectClassResponse pointBySubjectClassResponse = new PointBySubjectClassResponse(fullName, point, idUser, idPoint);
            responses.add(pointBySubjectClassResponse);
        }
        return responses;
    }

    @Override
    @Transactional
    public MessageResponse createPoint(Integer idSubject, Integer idUser, PostPointRequest postPointRequest) {
        Optional<User> userOptional = userRepository.findById(idUser);
        Optional<Subject> subjectOptional = subjectRepository.findById(idSubject);
        Point point = new Point();
        point.setUser(userOptional.get());
        point.setSubject(subjectOptional.get());
        point.setPointName(postPointRequest.getPoint());
        try {
            pointRepository.save(point);
            return MessageResponse.builder().message("OK").build();
        } catch (DataIntegrityViolationException e) {
            return MessageResponse.builder().message("Point already exists").build();
        }
    }


    @Override
    public List<PointBySubjectResponse> selectPointForUser(Integer id) {
        return pointRepository.selectPointForUser(id);
    }

    @Override
    public MessageResponse updatePoint(Integer idPoint, PostPointRequest postPointRequest) {
        Point point = pointRepository.findById(idPoint).get();
        point.setPointName(postPointRequest.getPoint());
        pointRepository.save(point);
        return MessageResponse.builder().message("OK").build();
    }

}
