package com.example.managercourse.service.impl;

import com.example.managercourse.dto.request.TeacherRequest;
import com.example.managercourse.dto.response.*;
import com.example.managercourse.entity.Course;
import com.example.managercourse.entity.CourseDetail;
import com.example.managercourse.entity.Role;
import com.example.managercourse.entity.User;
import com.example.managercourse.repository.CourseDetailRepository;
import com.example.managercourse.repository.CourseRepository;
import com.example.managercourse.repository.RoleRepository;
import com.example.managercourse.repository.UserRepository;
import com.example.managercourse.service.TeacherService;
import com.example.managercourse.util.GenerateUsernameUtil;
import com.example.managercourse.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseDetailRepository courseDetailRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
     * @param teacherRequest
     * @return
     */
    @Override
    @Transactional
    public MessageResponse createTeacher(TeacherRequest teacherRequest) {
        try {
            Role role = roleRepository.findByRole("TEACHER");
            List<User> userList = userRepository.findAll();
            if (role == null) {
                return MessageResponse.builder().message("Không tìm thấy role").build();
            }
            Integer count = userRepository.countUserByRole_Role("TEACHER");
            String username = GenerateUsernameUtil.generateUsername(teacherRequest.getFullName());
            User user = User.builder()
                    .fullName(teacherRequest.getFullName())
                    .codeName("TEACHER_" + count)
                    .gender(teacherRequest.getGender())
                    .phoneNumber(teacherRequest.getPhoneNumber())
                    .startDate(LocalDate.now())
                    .address(teacherRequest.getAddress())
                    .email(teacherRequest.getEmail())
                    .yearOfBirth(teacherRequest.getDateOfBirth())
                    .username(username + userList.size() + 1)
                    .status(1)
                    .password(passwordEncoder.encode(username))
                    .role(role)
                    .build();
            userRepository.save(user);

            for (String s: teacherRequest.getInputValue()) {
                System.out.println(s);
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

    @Override
    @Transactional
    public MessageResponse updateTeacher(Integer id, TeacherRequest teacherRequest) {
        try {
            User user = userRepository.findById(id).get();
            if (user == null) {
                return MessageResponse.builder().message("Không tìm thấy giảng viên").build();
            }

            user.setFullName(teacherRequest.getFullName());
            user.setGender(teacherRequest.getGender());
            user.setPhoneNumber(teacherRequest.getPhoneNumber());
            user.setStartDate(LocalDate.now());
            user.setAddress(teacherRequest.getAddress());
            user.setEmail(teacherRequest.getEmail());
            user.setYearOfBirth(teacherRequest.getDateOfBirth());
            userRepository.save(user);

            for (String s: teacherRequest.getInputValue()) {
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
        } catch (Exception e) {
            e.printStackTrace();
            return MessageResponse.builder().message("Đã xảy ra lỗi khi thêm giáo viên").build();
        }
    }

    @Override
    public UpdateTeacherResponse showDetailTeacher(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
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
        for (CourseDetail cd: user.getCourseDetailList()) {
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
