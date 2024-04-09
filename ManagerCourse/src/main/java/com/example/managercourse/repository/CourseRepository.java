package com.example.managercourse.repository;

import com.example.managercourse.dto.response.*;
import com.example.managercourse.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query("SELECT new com.example.managercourse.dto.response.CourseOfTeacherResponse(cs.id, cs.courseName) " +
            "FROM Course cs WHERE cs.status = 1")
    List<CourseOfTeacherResponse> findByCourseName();

    @Query("SELECT cs FROM Course cs ORDER BY cs.id desc")
    Page<Course> findAllCourse(Pageable pageable);

    @Query("SELECT cs FROM Course cs WHERE cs.courseName LIKE %:courseName% ORDER BY cs.id desc")
    Page<Course> searchCourse(Pageable pageable, @Param("courseName") String courseName);

    @Query("SELECT new com.example.managercourse.dto.response.TeacherOfClassResponse(u.id, u.fullName) FROM CourseDetail cd " +
            "JOIN cd.user u " +
            "JOIN cd.course cs " +
            "JOIN u.role rl " +
            "WHERE rl.roleName = 'TEACHER' AND cs.id = :id")
    List<TeacherOfClassResponse> getTeacherOfClass(@Param("id") Integer id);

    Course findByCourseName(String courseName);

    @Query("SELECT new com.example.managercourse.dto.response.CourseOfClass(cs.id, cs.courseName) " +
            "FROM Course cs JOIN cs.courseDetailList cd JOIN cd.user u WHERE u.username = :username")
    List<CourseOfClass> getListCourse(@Param("username") String username);
}
