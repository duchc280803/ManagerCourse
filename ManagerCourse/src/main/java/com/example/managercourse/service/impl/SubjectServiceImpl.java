package com.example.managercourse.service.impl;

import com.example.managercourse.dto.request.SubjectCreateRequest;
import com.example.managercourse.dto.request.UpdateSubjectRequest;
import com.example.managercourse.dto.response.MessageResponse;
import com.example.managercourse.dto.response.SubjectResponse;
import com.example.managercourse.dto.response.SubjectUpdateResponse;
import com.example.managercourse.entity.Schedule;
import com.example.managercourse.entity.Subject;
import com.example.managercourse.exception.NotFoundException;
import com.example.managercourse.repository.ClassRepository;
import com.example.managercourse.repository.ScheduleRepository;
import com.example.managercourse.repository.SubjectRepository;
import com.example.managercourse.service.SubjectService;
import com.example.managercourse.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository, ScheduleRepository scheduleRepository) {
        this.subjectRepository = subjectRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<SubjectResponse> getListSubjectName(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return subjectRepository.getListSubjectName(pageable).getContent();
    }

    @Override
    public List<SubjectResponse> getListSubjectByCourse(Integer pageNumber, Integer pageSize, Integer id, Integer classify) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return subjectRepository.getListSubjectByCourse(pageable, id, classify).getContent();
    }

    @Transactional
    @Override
    public MessageResponse createSubject(SubjectCreateRequest subjectCreateRequest) {
        Subject subject = Subject
                .builder()
                .subjectCode(subjectCreateRequest.getSubjectCode())
                .subjectName(subjectCreateRequest.getSubjectName())
                .curriculumContent(subjectCreateRequest.getCurriculumContent())
                .learningMode(subjectCreateRequest.getLearningMode())
                .classify(subjectCreateRequest.getClassify())
                .numberOfSessions(subjectCreateRequest.getNumberOfSessions())
                .build();
        subjectRepository.save(subject);
        return MessageResponse.builder().message("Thêm thành công").build();
    }

    @Override
    public SubjectUpdateResponse subjectDetail(Integer id) {
        Optional<Subject> subjectOptional = subjectRepository.findById(id);
        if (subjectOptional.isPresent()) {
            Subject subject = subjectOptional.get();
            return MapperUtil.toDTO(subject, SubjectUpdateResponse.class);
        } else {
            // Handle case when subject is not found
            throw new NotFoundException("Subject not found for id: " + id);
        }
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
        subject.setClassify(updateSubjectRequest.getClassify());
        subject.setNumberOfSessions(updateSubjectRequest.getNumberOfSessions());
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
    public List<SubjectResponse> getListSubjectByClass(Integer id) {
        return subjectRepository.getListSubjectByClass(id);
    }

    @Override
    public List<SubjectResponse> getListSubjectName(Integer id) {
        return subjectRepository.getListSubjectName(id);
    }

    @Override
    public List<SubjectResponse> getListSubjectAddCourse(Integer id) {
        return subjectRepository.getListSubjectAddCourse(id);
    }

    @Override
    public List<SubjectResponse> getListSubjectForClassSubjectSchedule(Integer idClass) {
        // Lấy danh sách tất cả các môn học của lớp
        List<SubjectResponse> subjectList = subjectRepository.getListSubjectByClass1(idClass);

        // Lấy danh sách tất cả các lịch học của lớp
        List<Schedule> scheduleList = scheduleRepository.findByAClassId(idClass);

        // Tạo một HashSet chứa ID của các môn học đã được xếp lịch
        Set<Integer> scheduledSubjectIds = new HashSet<>();
        for (Schedule schedule : scheduleList) {
            scheduledSubjectIds.add(schedule.getSubject().getId());
        }

        // Tạo danh sách để chứa các môn học có classify là 1
        List<SubjectResponse> subjectResponsesClassify1 = new ArrayList<>();

        // Tạo danh sách để chứa các môn học không có classify là 1
        List<SubjectResponse> subjectResponsesOther = new ArrayList<>();

        // Lọc ra những môn học chưa được xếp lịch và phân loại chúng vào danh sách tương ứng
        for (SubjectResponse subject : subjectList) {
            if (!scheduledSubjectIds.contains(subject.getId())) {
                if (subject.getClassify() == 1) {
                    subjectResponsesClassify1.add(subject);
                } else {
                    subjectResponsesOther.add(subject);
                }
            }
        }

        // Nếu danh sách các môn học có classify là 1 không rỗng, trả về danh sách này
        if (!subjectResponsesClassify1.isEmpty()) {
            return subjectResponsesClassify1;
        }
        // Nếu không, trả về danh sách các môn học không có classify là 1
        else {
            return subjectResponsesOther;
        }
    }

}
