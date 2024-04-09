package com.example.managercourse.service.impl;

import com.example.managercourse.dto.request.CourseRequest;
import com.example.managercourse.dto.request.SubjectAddCourseRequest;
import com.example.managercourse.dto.response.*;
import com.example.managercourse.entity.*;
import com.example.managercourse.exception.NotFoundException;
import com.example.managercourse.repository.*;
import com.example.managercourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final UserRepository userRepository;

    private final CourseDetailRepository courseDetailRepository;

    private final SubjectDetailRepository subjectDetailRepository;

    private final SubjectRepository subjectRepository;

    private static final String COURSE_NOT_FOUND_MESSAGE = "Course not found with id: %d";

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository,
                             UserRepository userRepository,
                             CourseDetailRepository courseDetailRepository,
                             SubjectDetailRepository subjectDetailRepository,
                             SubjectRepository subjectRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.courseDetailRepository = courseDetailRepository;
        this.subjectDetailRepository = subjectDetailRepository;
        this.subjectRepository = subjectRepository;
    }

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
    public List<TeacherOfClassResponse> fillTeacherByCourse(String courseName) {
        return userRepository.fillTeacherByCourse(courseName);
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
                .image(courseRequest.getImage())
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
                .orElseThrow(() -> new IllegalArgumentException(String.format(COURSE_NOT_FOUND_MESSAGE, id)));

        List<String> teacherNames = course.getCourseDetailList().stream()
                .map(courseDetail -> courseDetail.getUser().getFullName())
                .toList();

        return CourseDetailResponse.builder()
                .id(course.getId())
                .courseCode(course.getCourseCode())
                .courseName(course.getCourseName())
                .coursePrice(course.getCoursePrice())
                .description(course.getDescription())
                .scheduled(course.getScheduled())
                .status(course.getStatus())
                .nameTeacher(teacherNames)
                .image(course.getImage())
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
        course.setImage(courseRequest.getImage());
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
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();

            List<Subject> subjectList = subjectRepository.findAllById(subjectAddCourseRequest.getSelectedSubjectIds());
            subjectList.forEach(subject -> {
                SubjectDetail subjectDetail = SubjectDetail.builder()
                        .course(course)
                        .subject(subject)
                        .build();
                subjectDetailRepository.save(subjectDetail);
            });

            return MessageResponse.builder().message("Thêm thành công").build();
        } else {
            throw new NotFoundException("Course not found with id: " + id);
        }
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
        courseResponse.setImage(course.getImage());
        courseResponse.setTeacherOfClassResponses(teacherOfClassResponses);
        return courseResponse;
    }
}
