package com.example.managercourse.service.impl;

import com.example.managercourse.dto.request.TeacherRequest;
import com.example.managercourse.dto.response.*;
import com.example.managercourse.entity.*;
import com.example.managercourse.exception.NotFoundException;
import com.example.managercourse.repository.*;
import com.example.managercourse.service.TeacherService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final UserRepository userRepository;

    private final CourseRepository courseRepository;

    private final CourseDetailRepository courseDetailRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final JavaMailSenderImpl javaMailSender;

    private final MailServerRepository mailServerRepository;

    private final EmailTemplateRepository emailTemplateRepository;

    @Autowired
    public TeacherServiceImpl(
            UserRepository userRepository,
            CourseRepository courseRepository,
            CourseDetailRepository courseDetailRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            JavaMailSenderImpl javaMailSender,
            MailServerRepository mailServerRepository,
            EmailTemplateRepository emailTemplateRepository
    ) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.courseDetailRepository = courseDetailRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.javaMailSender = javaMailSender;
        this.mailServerRepository = mailServerRepository;
        this.emailTemplateRepository = emailTemplateRepository;
    }

    /**
     * Get list teacher
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public List<TeacherResponse> showListTeacher(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<User> userPage = userRepository.showListTeacher(pageable);
        List<TeacherResponse> teacherResponses = new ArrayList<>();
        userPage.getContent().forEach(up -> {
            List<CourseOfClass> courseOfClassList = userRepository.getCourseOfClass(up.getId());
            TeacherResponse teacherResponse = teacherResponse(up, courseOfClassList);
            teacherResponses.add(teacherResponse);
        });
        return teacherResponses;
    }

    /**
     * Created new a teacher
     *
     * @param teacherRequest
     * @return
     */
    @Override
    @Transactional
    @Async
    public MessageResponse createTeacher(TeacherRequest teacherRequest) {
        EmailTemplate emailTemplate = emailTemplateRepository.findByTypeTemplate(2);
        EmailServer emailServer = mailServerRepository.findByStatus(1);
        try {
            Role role = roleRepository.findByRoleName("TEACHER");
            if (role == null) {
                return MessageResponse.builder().message("Không tìm thấy role").build();
            }
            Integer count = userRepository.countUserByRole_RoleName("TEACHER");
            String password = UsernamePasswordGenerator.generatePassword();
            String encodedPassword = passwordEncoder.encode(password);
            User user = User.builder()
                    .fullName(teacherRequest.getFullName())
                    .codeName("TEACHER_" + count)
                    .gender(teacherRequest.getGender())
                    .phoneNumber(teacherRequest.getPhoneNumber())
                    .startDate(LocalDate.now())
                    .address(teacherRequest.getAddress())
                    .email(teacherRequest.getEmail())
                    .yearOfBirth(teacherRequest.getDateOfBirth())
                    .status(1)
                    .username(UsernamePasswordGenerator.generateUsername(teacherRequest.getFullName()))
                    .password(encodedPassword)
                    .role(role)
                    .build();
            userRepository.save(user);

            String emailContent = createEmailContent(user.getFullName(), user.getEmail(), user.getPhoneNumber(), user.getUsername(), password, emailTemplate.getContent());
            JavaMailSenderUtl.send(user.getEmail(), emailTemplate.getSubject(), emailContent, javaMailSender, emailServer.getUsername(), emailServer.getPassword());

            for (String s : teacherRequest.getInputValue()) {
                Course course = courseRepository.findByCourseName(s);
                if (course == null) {
                    return MessageResponse.builder().message("Không tìm thấy khóa học").build();
                }

                CourseDetail courseDetail = new CourseDetail();
                courseDetail.setCourse(course);
                courseDetail.setUser(user);
                courseDetailRepository.save(courseDetail);
            }


            return MessageResponse.builder().message("Thêm thành công").build();
        } catch (Exception e) {
            e.printStackTrace();
            return MessageResponse.builder().message("Đã xảy ra lỗi khi thêm giáo viên").build();
        }
    }

    private String createEmailContent(String fullName, String email, String phoneNumber, String username, String password, String emailTemplate) {
        // Thực hiện thay thế các giá trị vào nội dung email
        emailTemplate = emailTemplate
                .replace("[Tên giảng viên]", fullName)
                .replace("[Email giảng viên]", email)
                .replace("[Số điện thoại giảng viên]", phoneNumber)
                .replace("[username]", username)
                .replace("[password]", password);

        // Thêm ký tự xuống dòng vào nội dung email
        emailTemplate += "\n\n"; // Thêm hai ký tự xuống dòng để tạo khoảng cách giữa các phần

        return emailTemplate;
    }

    @Override
    @Transactional
    public MessageResponse updateTeacher(Integer id, TeacherRequest teacherRequest) {
        try {
            Optional<User> userOptional = userRepository.findById(id);
            if (userOptional.isEmpty()) {
                throw new NotFoundException("User not found");
            }

            User user = userOptional.get();
            user.setFullName(teacherRequest.getFullName());
            user.setGender(teacherRequest.getGender());
            user.setPhoneNumber(teacherRequest.getPhoneNumber());
            user.setStartDate(LocalDate.now());
            user.setAddress(teacherRequest.getAddress());
            user.setEmail(teacherRequest.getEmail());
            user.setYearOfBirth(teacherRequest.getDateOfBirth());
            userRepository.save(user);

            for (String s : teacherRequest.getInputValue()) {
                Course course = courseRepository.findByCourseName(s);
                if (course == null) {
                    return MessageResponse.builder().message("Không tìm thấy khóa học").build();
                }

                CourseDetail courseDetail = new CourseDetail();
                courseDetail.setCourse(course);
                courseDetail.setUser(user);
                courseDetailRepository.save(courseDetail);
            }

            return MessageResponse.builder().message("Cập nhập thành công").build();
        } catch (NotFoundException e) {
            return MessageResponse.builder().message("Không tìm thấy người dùng").build();
        } catch (Exception e) {
            e.printStackTrace();
            return MessageResponse.builder().message("Đã xảy ra lỗi khi thêm giáo viên").build();
        }
    }

    @Override
    public UpdateTeacherResponse showDetailTeacher(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            // Handle the case where user is not found
            throw new NotFoundException("User not found with id: " + id);
        }

        User user = userOptional.get();
        UpdateTeacherResponse teacherResponse = new UpdateTeacherResponse();
        teacherResponse.setId(user.getId());
        teacherResponse.setFullName(user.getFullName());
        teacherResponse.setAddress(user.getAddress());
        teacherResponse.setEmail(user.getEmail());
        teacherResponse.setPhoneNumber(user.getPhoneNumber());
        teacherResponse.setYearOfBirth(user.getYearOfBirth());
        teacherResponse.setGender(user.getGender());

        List<String> courses = new ArrayList<>();
        for (CourseDetail cd : user.getCourseDetailList()) {
            courses.add(cd.getCourse().getCourseName());
        }
        teacherResponse.setInputValue(courses);

        return teacherResponse;
    }

    @Override
    public List<TeacherSearchResponse> findByTeacher(Integer pageNumber, Integer pageSize, String teacherName) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<User> userPage = userRepository.teacherSearch(pageable, teacherName);
        List<TeacherSearchResponse> teacherSearchResponseList = new ArrayList<>();
        userPage.getContent().forEach(up -> {
            List<CourseOfClass> courseOfClassList = userRepository.getCourseOfClass(up.getId());
            TeacherSearchResponse teacherResponse = teacherSearchResponse(up, courseOfClassList);
            teacherSearchResponseList.add(teacherResponse);
        });
        return teacherSearchResponseList;
    }

    private TeacherSearchResponse teacherSearchResponse(User user, List<CourseOfClass> courseOfClassList) {
        TeacherSearchResponse teacherResponse = new TeacherSearchResponse();
        teacherResponse.setFullName(user.getFullName());
        teacherResponse.setEmail(user.getEmail());
        teacherResponse.setAddress(user.getAddress());
        teacherResponse.setGender(user.getGender());
        teacherResponse.setPhoneNumber(user.getPhoneNumber());
        teacherResponse.setYearOfBirth(user.getYearOfBirth());
        teacherResponse.setAge(user.getId());
        teacherResponse.setCourseOfClassList(courseOfClassList);
        return teacherResponse;
    }

    private TeacherResponse teacherResponse(User user, List<CourseOfClass> courseOfClassList) {
        TeacherResponse teacherResponse = new TeacherResponse();
        teacherResponse.setId(user.getId());
        teacherResponse.setCodeName(user.getCodeName());
        teacherResponse.setFullName(user.getFullName());
        teacherResponse.setEmail(user.getEmail());
        teacherResponse.setAddress(user.getAddress());
        teacherResponse.setGender(user.getGender());
        teacherResponse.setPhoneNumber(user.getPhoneNumber());
        teacherResponse.setYearOfBirth(user.getYearOfBirth());
        teacherResponse.setStatus(user.getStatus());
        teacherResponse.setCourseOfClassList(courseOfClassList);
        return teacherResponse;
    }
}
