package com.example.managercourse.service.impl;

import com.example.managercourse.dto.request.CourseRequest;
import com.example.managercourse.dto.request.SubjectAddCourseRequest;
import com.example.managercourse.dto.response.*;
import com.example.managercourse.entity.*;
import com.example.managercourse.exception.NotFoundException;
import com.example.managercourse.repository.*;
import com.example.managercourse.service.CourseService;
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
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseDetailRepository courseDetailRepository;

    @Autowired
    private SubjectDetailRepository subjectDetailRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public List<CourseResponse> showCourse(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Course> coursePage = courseRepository.findAllCourse(pageable);
        List<CourseResponse> courseResponseList = new ArrayList<>();

        coursePage.getContent().forEach(cr -> {
            List<SubjectDetail> courseDetailList = courseDetailRepository.count(cr.getId());
            List<TeacherOfClassResponse> teacherOfClassResponse = courseRepository.getTeacherOfClass(cr.getId());
            CourseResponse courseResponse = courseResponse(cr, teacherOfClassResponse, courseDetailList.size());
            courseResponseList.add(courseResponse);
        });
        return courseResponseList;
    }

    @Override
    public List<CourseOfTeacherResponse> findByCourseName() {
        return courseRepository.findByCourseName();
    }

    @Override
    public List<TeacherOfClassResponse> fillTeacher() {
        return userRepository.fillTeacher();
    }

    @Override
    @Transactional
    public MessageResponse createCourse(CourseRequest courseRequest) {
        Course course = Course.builder()
                .courseCode(courseRequest.getCourseCode())
                .courseName(courseRequest.getCourseName())
                .coursePrice(courseRequest.getCoursePrice())
                .createAt(LocalDate.now())
                .description(courseRequest.getDescription())
                .status(1)
                .scheduled(courseRequest.getScheduled())
                .build();

        courseRepository.save(course);

        courseRequest.getNameTeacher().forEach(username -> {
            Optional<User> userOptional = userRepository.findByFullName(username);
            userOptional.ifPresent(user -> {
                CourseDetail courseDetail = new CourseDetail();
                courseDetail.setCourse(course);
                courseDetail.setUser(user);
                courseDetailRepository.save(courseDetail);
            });
        });

        return MessageResponse.builder().message("Thêm thành công").build();
    }

    @Override
    public List<CourseResponse> searchCourse(Integer pageNumber, Integer pageSize, String nameCourse) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Course> coursePage = courseRepository.searchCourse(pageable, nameCourse);
        List<CourseResponse> courseResponseList = new ArrayList<>();
        coursePage.getContent().forEach(cr -> {
            List<TeacherOfClassResponse> teacherOfClassResponse = courseRepository.getTeacherOfClass(cr.getId());
            CourseResponse courseResponse = courseResponse(cr, teacherOfClassResponse, teacherOfClassResponse.size());
            courseResponseList.add(courseResponse);
        });
        return courseResponseList;
    }

    @Override
    public CourseDetailResponse detailCourse(Integer id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with id: " + id));

        List<String> teacherNames = course.getCourseDetailList().stream()
                .map(courseDetail -> courseDetail.getUser().getFullName())
                .collect(Collectors.toList());

        return CourseDetailResponse.builder()
                .id(course.getId())
                .courseCode(course.getCourseCode())
                .courseName(course.getCourseName())
                .coursePrice(course.getCoursePrice())
                .description(course.getDescription())
                .scheduled(course.getScheduled())
                .status(course.getStatus())
                .nameTeacher(teacherNames)
                .build();
    }

    @Override
    public MessageResponse updateCourse(Integer id, CourseRequest courseRequest) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with id: " + id));

        course.setCourseCode(courseRequest.getCourseCode());
        course.setCourseName(courseRequest.getCourseName());
        course.setCoursePrice(courseRequest.getCoursePrice());
        course.setDescription(courseRequest.getDescription());
        course.setStatus(courseRequest.getStatus());
        course.setScheduled(courseRequest.getScheduled());
        courseRepository.save(course);
        for (String teacherName : courseRequest.getNameTeacher()) {
            User user = userRepository.findByFullName(teacherName)
                    .orElseThrow(() -> new IllegalArgumentException("User not found with name: " + teacherName));

            CourseDetail courseDetail = courseDetailRepository.findByCourseAndUser(course, user);
            if (courseDetail == null) {
                courseDetail = new CourseDetail();
                courseDetail.setUser(user);
                courseDetail.setCourse(course);
                courseDetailRepository.save(courseDetail);
            }
        }

        return MessageResponse.builder().message("Update thành công").build();
    }

    @Override
    public List<CourseOfClass> getListCourse(String username) {
        return courseRepository.getListCourse(username);
    }

    @Override
    @Transactional
    public MessageResponse addSubjectToCourse(SubjectAddCourseRequest subjectAddCourseRequest, Integer id) {
        Course course = courseRepository.findById(id).get();

        List<Subject> subjectList = subjectRepository.findAllById(subjectAddCourseRequest.getSelectedSubjectIds());
        subjectList.forEach(subject -> {
            SubjectDetail SubjectDetail = com.example.managercourse.entity
                    .SubjectDetail
                    .builder()
                    .course(course)
                    .subject(subject)
                    .build();
            subjectDetailRepository.save(SubjectDetail);
        });
        return MessageResponse.builder().message("Thêm thành công").build();
    }


    private CourseResponse courseResponse(Course course, List<TeacherOfClassResponse> teacherOfClassResponses, int count) {
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setId(course.getId());
        courseResponse.setCourseCode(course.getCourseCode());
        courseResponse.setCourseName(course.getCourseName());
        courseResponse.setCoursePrice(course.getCoursePrice());
        courseResponse.setDescription(course.getDescription());
        courseResponse.setQuantitySubject(count);
        courseResponse.setScheduled(course.getScheduled());
        courseResponse.setStatus(course.getStatus());
        courseResponse.setTeacherOfClassResponses(teacherOfClassResponses);
        return courseResponse;
    }
}
