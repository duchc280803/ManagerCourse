package com.example.managercourse.service.impl;

import com.example.managercourse.dto.request.StudentRequest;
import com.example.managercourse.dto.response.*;
import com.example.managercourse.entity.Course;
import com.example.managercourse.entity.CourseDetail;
import com.example.managercourse.entity.Role;
import com.example.managercourse.entity.User;
import com.example.managercourse.exception.NotFoundException;
import com.example.managercourse.repository.*;
import com.example.managercourse.service.StudentService;
import com.example.managercourse.util.MapperUtil;
import com.example.managercourse.util.UsernamePasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CourseDetailRepository courseDetailRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<StudentResponse> showListStudent(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<StudentResponse> userPage = userRepository.showListStudent(pageable);
        return userPage.getContent();
    }

    @Override
    @Transactional
    public MessageResponse createStudent(StudentRequest studentRequest) {
        Role role = roleRepository.findByRole("STUDENT");
        Integer count = userRepository.countUserByRole_Role("STUDENT");
        User user = User
                .builder()
                .codeName("HOC_VIEN_" + count)
                .fullName(studentRequest.getFullName())
                .gender(studentRequest.getGender())
                .yearOfBirth(studentRequest.getDateOfBirth())
                .email(studentRequest.getEmail())
                .address(studentRequest.getAddress())
                .phoneNumber(studentRequest.getPhoneNumber())
                .username(UsernamePasswordGenerator.generateUsername(studentRequest.getFullName()))
                .password(passwordEncoder.encode(UsernamePasswordGenerator.generatePassword()))
                .status(1)
                .role(role)
                .build();
        userRepository.save(user);
        Course course = courseRepository.findByCourseName(studentRequest.getCourseName());
        CourseDetail courseDetail = new CourseDetail();
        courseDetail.setCourse(course);
        courseDetail.setUser(user);
        courseDetailRepository.save(courseDetail);
        return MessageResponse.builder().message("Thêm thành công").build();
    }

    @Override
    public List<StudentSearchResponse> searchStudent(Integer pageNumber, Integer pageSize, String nameStudent) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<StudentSearchResponse> studentSearchResponsePage = userRepository.searchStudent(pageable, nameStudent);
        return studentSearchResponsePage.getContent();
    }

    @Override
    public List<StudentCbResponse> comboboxStudent() {
        return userRepository.comboboxStudent();
    }

    @Override
    public StudentResponse studentDetail(Integer id) {
        return userRepository.studentDetail(id);
    }

    @Override
    @Transactional
    public MessageResponse updateStudent(Integer id, StudentRequest studentRequest) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            updateUserFields(user, studentRequest);
            updateCourseDetail(user, studentRequest.getCourseName());
            return MessageResponse.builder().message("Update thành công").build();
        } else {
            return MessageResponse.builder().message("Không tìm thấy sinh viên").build();
        }
    }

    @Override
    public List<StudentCheckBoxResponse> showListStudent(String courseName) {
        return userRepository.showListStudent(courseName);
    }

    @Override
    public List<CourseOfStudent> getListCourseOfStudent(String name, String courseName, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<CourseOfStudent> courseOfStudentPage = classRepository.getListCourseOfStudent(name, courseName, pageable);
        return courseOfStudentPage.getContent();
    }

    private void updateUserFields(User user, StudentRequest studentRequest) {
        user.setAddress(studentRequest.getAddress());
        user.setFullName(studentRequest.getFullName());
        user.setGender(studentRequest.getGender());
        user.setEmail(studentRequest.getEmail());
        user.setPhoneNumber(studentRequest.getPhoneNumber());
        user.setYearOfBirth(studentRequest.getDateOfBirth());
    }

    private void updateCourseDetail(User user, String courseName) {
        Course course = courseRepository.findByCourseName(courseName);
        System.out.println(course.getId());
        if (course == null) {
            throw new NotFoundException("Không tìm thấy khóa học này");
        }
        CourseDetail courseDetail = courseDetailRepository.findByCourseAndUser(course, user);
        if (courseDetail != null) {
            courseDetail.setCourse(course);
            courseDetailRepository.save(courseDetail);
        } else {
            throw new NotFoundException("Không tìm thấy chi tiết khóa học cho sinh viên này");
        }
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
