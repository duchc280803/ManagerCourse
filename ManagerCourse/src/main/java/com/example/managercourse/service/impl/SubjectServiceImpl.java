package com.example.managercourse.service.impl;

import com.example.managercourse.dto.request.SubjectCreateRequest;
import com.example.managercourse.dto.request.UpdateSubjectRequest;
import com.example.managercourse.dto.response.MessageResponse;
import com.example.managercourse.dto.response.SubjectResponse;
import com.example.managercourse.dto.response.SubjectUpdateResponse;
import com.example.managercourse.entity.Course;
import com.example.managercourse.entity.Subject;
import com.example.managercourse.repository.CourseRepository;
import com.example.managercourse.repository.SubjectRepository;
import com.example.managercourse.service.SubjectService;
import com.example.managercourse.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public List<SubjectResponse> getListSubjectName(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return subjectRepository.getListSubjectName(pageable).getContent();
    }

    @Override
    public List<SubjectResponse> getListSubjectByCourse(Integer pageNumber, Integer pageSize, Integer id) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return subjectRepository.getListSubjectByCourse(pageable, id).getContent();
    }

    @Transactional
    @Override
    public MessageResponse createSubject(SubjectCreateRequest subjectCreateRequest) {
        Subject subject = Subject
                .builder()
                .subjectCode(subjectCreateRequest.getSubjectCode())
                .subjectName(subjectCreateRequest.getSubjectName())
                .curriculumContent(subjectCreateRequest.getCurriculumContent())
                .studyTimeEnd(subjectCreateRequest.getStudyTimeEnd())
                .studyTimeStart(subjectCreateRequest.getStudyTimeStart())
                .learningMode(subjectCreateRequest.getLearningMode())
                .classify(subjectCreateRequest.getClassify())
                .build();
        subjectRepository.save(subject);
        return MessageResponse.builder().message("Thêm thành công").build();
    }

    @Override
    public SubjectUpdateResponse subjectDetail(Integer id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        return MapperUtil.toDTO(subject.get(), SubjectUpdateResponse.class);
    }

    @Transactional
    @Override
    public MessageResponse updateSubject(Integer id, UpdateSubjectRequest updateSubjectRequest) {
        Optional<Subject> subjectOptional = subjectRepository.findById(id);

        if (subjectOptional.isPresent()) {
            Subject subject = subjectOptional.get();
            updateSubjectFields(subject, updateSubjectRequest);
            subjectRepository.save(subject);
            return MessageResponse.builder().message("Update thành công").build();
        } else {
            return MessageResponse.builder().message("Không tìm thấy môn học").build();
        }
    }
    private void updateSubjectFields(Subject subject, UpdateSubjectRequest updateSubjectRequest) {
        subject.setSubjectName(updateSubjectRequest.getSubjectName());
        subject.setCurriculumContent(updateSubjectRequest.getCurriculumContent());
        subject.setLearningMode(updateSubjectRequest.getLearningMode());
        subject.setStudyTimeStart(updateSubjectRequest.getStudyTimeStart());
        subject.setStudyTimeEnd(updateSubjectRequest.getStudyTimeEnd());
        subject.setClassify(updateSubjectRequest.getClassify());
    }

    @Override
    public List<SubjectResponse> searchListSubject(Integer pageNumber, Integer pageSize, String subjectName) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return subjectRepository.searchListSubject(pageable, subjectName).getContent();
    }

    @Override
    public List<SubjectResponse> getListSubjectByCourse(String courseName) {
        return subjectRepository.getListSubjectByCourse(courseName);
    }

    @Override
    public List<SubjectResponse> getListSubjectByClass(Integer id, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<SubjectResponse> subjectResponses = subjectRepository.getListSubjectByClass(id, pageable);
        return subjectResponses.getContent();
    }

    @Override
    public List<SubjectResponse> getListSubjectName(Integer id) {
        return subjectRepository.getListSubjectName(id);
    }

    @Override
    public List<SubjectResponse> getListSubjectAddCourse(Integer id) {
        return subjectRepository.getListSubjectAddCourse(id);
    }

}
