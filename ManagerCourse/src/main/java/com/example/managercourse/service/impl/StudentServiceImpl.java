package com.example.managercourse.service.impl;

import com.example.managercourse.dto.request.RegisterUserForCourseRequest;
import com.example.managercourse.dto.request.StudentRequest;
import com.example.managercourse.dto.response.*;
import com.example.managercourse.entity.*;
import com.example.managercourse.exception.NotFoundException;
import com.example.managercourse.repository.*;
import com.example.managercourse.service.StudentService;
import com.example.managercourse.util.JavaMailSenderUtl;
import com.example.managercourse.util.UsernamePasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final UserRepository userRepository;

    private final CourseRepository courseRepository;

    private final RoleRepository roleRepository;

    private final CourseDetailRepository courseDetailRepository;

    private final ClassRepository classRepository;

    private final PasswordEncoder passwordEncoder;

    private final JavaMailSenderImpl javaMailSender;

    private final MailServerRepository mailServerRepository;

    private final EmailTemplateRepository emailTemplateRepository;

    @Autowired
    public StudentServiceImpl(
            UserRepository userRepository,
            CourseRepository courseRepository,
            RoleRepository roleRepository,
            CourseDetailRepository courseDetailRepository,
            ClassRepository classRepository,
            PasswordEncoder passwordEncoder,
            JavaMailSenderImpl javaMailSender,
            MailServerRepository mailServerRepository,
            EmailTemplateRepository emailTemplateRepository
    ) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.roleRepository = roleRepository;
        this.courseDetailRepository = courseDetailRepository;
        this.classRepository = classRepository;
        this.passwordEncoder = passwordEncoder;
        this.javaMailSender = javaMailSender;
        this.mailServerRepository = mailServerRepository;
        this.emailTemplateRepository = emailTemplateRepository;
    }

    @Override
    public List<StudentResponse> showListStudent(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<StudentResponse> userPage = userRepository.showListStudent(pageable);
        return userPage.getContent();
    }

    @Override
    @Transactional
    @Async
    public MessageResponse createStudent(StudentRequest studentRequest) {
        EmailTemplate emailTemplate = emailTemplateRepository.findByTypeTemplate(1);
        EmailServer emailServer = mailServerRepository.findByStatus(1);
        Role role = roleRepository.findByRoleName("STUDENT");
        Integer count = userRepository.countUserByRole_RoleName("STUDENT");
        String password = UsernamePasswordGenerator.generatePassword();
        String encodedPassword = passwordEncoder.encode(password);
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
                .password(encodedPassword)
                .status(1)
                .role(role)
                .build();
        userRepository.save(user);
        Course course = courseRepository.findByCourseName(studentRequest.getCourseName());
        CourseDetail courseDetail = new CourseDetail();
        courseDetail.setCourse(course);
        courseDetail.setUser(user);
        courseDetailRepository.save(courseDetail);

        String emailContent = createEmailContent(user.getPhoneNumber(), user.getFullName(), user.getUsername(), course.getCourseName(), password, LocalDate.now(), emailTemplate.getContent());
        JavaMailSenderUtl.send(user.getEmail(), emailTemplate.getSubject(), emailContent, javaMailSender, emailServer.getUsername(), emailServer.getPassword());
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

    @Override
    public MessageResponse createStudentForCourse(RegisterUserForCourseRequest registerUserForCourseRequest) {
        Role role = roleRepository.findByRoleName("STUDENT");
        Integer count = userRepository.countUserByRole_RoleName("STUDENT");
        EmailTemplate emailTemplate = emailTemplateRepository.findByTypeTemplate(1);
        EmailServer emailServer = mailServerRepository.findByStatus(1);
        String password = UsernamePasswordGenerator.generatePassword();
        String encodedPassword = passwordEncoder.encode(password);
        User user = User
                .builder()
                .codeName("HOC_VIEN_" + count)
                .fullName(registerUserForCourseRequest.getFullName())
                .email(registerUserForCourseRequest.getEmail())
                .phoneNumber(registerUserForCourseRequest.getPhoneNumber())
                .username(UsernamePasswordGenerator.generateUsername(registerUserForCourseRequest.getFullName()))
                .password(encodedPassword)
                .status(1)
                .role(role)
                .build();
        userRepository.save(user);
        Course course = courseRepository.findByCourseName("Backend Java");
        CourseDetail courseDetail = new CourseDetail();
        courseDetail.setCourse(course);
        courseDetail.setUser(user);
        courseDetailRepository.save(courseDetail);

        String emailContent = createEmailContent(user.getPhoneNumber(), user.getFullName(), user.getUsername(), course.getCourseName(), password, LocalDate.now(), emailTemplate.getContent());
        JavaMailSenderUtl.send(user.getEmail(), emailTemplate.getSubject(), emailContent, javaMailSender, emailServer.getUsername(), emailServer.getPassword());
        return MessageResponse.builder().message("Thêm thành công").build();
    }

    private String createEmailContent(String phoneNumber, String fullName, String username, String courseName, String password, LocalDate date, String emailTemplate) {
        // Thực hiện thay thế các giá trị vào nội dung email
        emailTemplate = emailTemplate
                .replace("[Tên học viên]", fullName)
                .replace("[Số điện thoại học viên] ", phoneNumber)
                .replace("[Tên khóa học]", courseName)
                .replace("[Thời gian]", date.toString())
                .replace("[username]", username)
                .replace("[password]", password);

        // Thêm ký tự xuống dòng vào nội dung email
        emailTemplate += "\n\n"; // Thêm hai ký tự xuống dòng để tạo khoảng cách giữa các phần

        return emailTemplate;
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
        if (course == null) {
            throw new NotFoundException("Không tìm thấy khóa học này");
        }

        CourseDetail courseDetail = courseDetailRepository.findByCourseAndUser(course, user);
        if (courseDetail == null) {
            throw new NotFoundException("Không tìm thấy chi tiết khóa học cho sinh viên này");
        }

        courseDetail.setCourse(course);
        courseDetailRepository.save(courseDetail);
    }


    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
