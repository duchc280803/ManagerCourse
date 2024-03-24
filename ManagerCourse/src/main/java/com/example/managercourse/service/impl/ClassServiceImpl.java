package com.example.managercourse.service.impl;

import com.example.managercourse.dto.request.ClassRequest;
import com.example.managercourse.dto.request.StudentAddClassRequest;
import com.example.managercourse.dto.response.*;
import com.example.managercourse.entity.*;
import com.example.managercourse.entity.Class;
import com.example.managercourse.exception.NotFoundException;
import com.example.managercourse.repository.*;
import com.example.managercourse.service.ClassService;
import com.example.managercourse.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ClassDetailRepository classDetailRepository;

    @Override
    public List<ClassResponse> getClassResponseList(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return classRepository.findAllClass(pageable).getContent();
    }

    @Override
    public List<ClassResponse> searchClass(Integer pageNumber, Integer pageSize, String className) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return classRepository.searchClass(pageable, className).getContent();
    }

    @Override
    @Transactional
    public MessageResponse createClass(ClassRequest classRequest) {
        // Lấy thông tin giáo viên từ tên giáo viên
        User teacher = userRepository.findByFullName(classRequest.getTeacherName())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giáo viên"));

        // Lấy thông tin môn học từ tên môn học
        Course course = courseRepository.findByCourseName(classRequest.getCourseName());

        // Tạo đối tượng lớp học
        Class aClass = Class.builder()
                .classCode(classRequest.getClassCode())
                .className(classRequest.getClassName())
                .requirements(classRequest.getRequired())
                .quantityStudent(classRequest.getQuantityStudent())
                .status(1)
                .course(course)
                .createAt(LocalDate.now())
                .userTeacher(teacher)
                .build();

        // Lưu thông tin lớp học vào cơ sở dữ liệu
        classRepository.save(aClass);

        // Trả về thông báo thành công
        return MessageResponse.builder().message("Thêm thành công").build();
    }

    @Override
    public UserInformationResponse teacherInformation(Integer id) {
        return userRepository.teacherInformation(id);
    }

    @Override
    public List<UserInformationResponse> listStudentInformation(Integer pageNumber, Integer pageSize, Integer id) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return classRepository.listStudentInformation(pageable, id).getContent();
    }

    @Override
    public ClassDetailResponse classDetail(Integer id) {
        return classRepository.detailClass(id);
    }

    @Override
    public MessageResponse updateClass(Integer id, ClassRequest classRequest) {
        Course course = courseRepository.findByCourseName(classRequest.getCourseName());
        Optional<User> userOptional = userRepository.findByFullName(classRequest.getTeacherName());
        if (userOptional.isEmpty()) {
            return MessageResponse.builder().message("Không tìm thấy giáo viên").build();
        }
        Class aClass = classRepository.findById(id).get();
        updateClassField(aClass, classRequest, course, userOptional.get());
        classRepository.save(aClass);
        return MessageResponse.builder().message("Update thành công").build();
    }

    @Override
    @Transactional
    public MessageResponse addStudentToClass(StudentAddClassRequest studentAddClassRequest, Integer idClass) {
        Class aClass = classRepository.findById(idClass).orElseThrow(() -> new NotFoundException("Class not found"));

        List<User> users = userRepository.findAllById(studentAddClassRequest.getSelectedStudentIds());

        users.forEach(user -> {
            ClassDetail classDetail = new ClassDetail();
            classDetail.setUser(user);
            classDetail.setAClass(aClass);
            classDetailRepository.save(classDetail);
        });

        return MessageResponse.builder().message("Thêm thành công học viên vào lớp").build();
    }

    @Override
    public List<ClassResponse> findAllClassName() {
        return classRepository.findAllClassName();
    }

    @Override
    public List<ClassResponse> findAllClassNameOfStudent(String username) {
        return classRepository.findAllClassNameOfStudent(username);
    }

    public void updateClassField(Class aClass, ClassRequest classRequest, Course course, User user) {
        aClass.setClassCode(classRequest.getClassCode());
        aClass.setClassName(classRequest.getClassName());
        aClass.setRequirements(classRequest.getRequired());
        aClass.setQuantityStudent(classRequest.getQuantityStudent());
        aClass.setStatus(classRequest.getStatus());
        aClass.setCourse(course);
        aClass.setUserTeacher(user);
    }
}