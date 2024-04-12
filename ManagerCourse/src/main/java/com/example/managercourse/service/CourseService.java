package com.example.managercourse.service;

import com.example.managercourse.dto.request.CourseRequest;
import com.example.managercourse.dto.request.SubjectAddCourseRequest;
import com.example.managercourse.dto.response.*;
import com.example.managercourse.entity.Course;

import java.util.List;

public interface CourseService {

    List<CourseResponse> showCourse(Integer pageNumber, Integer pageSize);

    List<CourseOfTeacherResponse> findByCourseName();

    List<TeacherOfClassResponse> fillTeacher();

    List<TeacherOfClassResponse> fillTeacherByCourse(String courseName);

    MessageResponse createCourse(CourseRequest courseRequest);

    List<CourseResponse> searchCourse(Integer pageNumber, Integer pageSize, String nameCourse);

    CourseDetailResponse detailCourse(Integer id);

    MessageResponse updateCourse(Integer id ,CourseRequest courseRequest);

    List<CourseOfClass> getListCourse(String username);

    MessageResponse addSubjectToCourse(SubjectAddCourseRequest subjectAddCourseRequest, Integer id);

    Course getCourseId(Integer id);
}
