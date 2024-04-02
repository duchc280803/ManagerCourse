package com.example.managercourse.repository;

import com.example.managercourse.dto.response.*;
import com.example.managercourse.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT new com.example.managercourse.dto.response.StudentResponse(u.id, u.codeName, u.fullName, u.gender," +
            "u.yearOfBirth, u.startDate, u.phoneNumber, u.email, u.address, cs.courseName, u.status) " +
            "FROM User u JOIN u.role rl JOIN u.courseDetailList cd JOIN cd.course cs " +
            "WHERE rl.role = 'STUDENT' order by u.id desc")
    Page<StudentResponse> showListStudent(Pageable pageable);

    @Query("SELECT u FROM User u JOIN u.role rl WHERE rl.role = 'TEACHER' order by u.startDate desc")
    Page<User> showListTeacher(Pageable pageable);

    @Query("SELECT new com.example.managercourse.dto.response.CourseOfClass(cs.id, cs.courseName) " +
            "FROM CourseDetail cd " +
            "JOIN cd.user u " +
            "JOIN cd.course cs " +
            "JOIN u.role rl " +
            "WHERE rl.role = 'TEACHER' AND u.id = :id")
    List<CourseOfClass> getCourseOfClass(@Param("id") Integer id);

    Optional<User> findByUsername(String name);

    @Query("SELECT u FROM User u JOIN u.role rl WHERE rl.role = 'TEACHER' AND u.fullName = :name")
    Optional<User> findByFullName(String name);

    @Query("SELECT u FROM User u JOIN u.role rl WHERE rl.role = 'STUDENT' AND u.fullName = :name")
    Optional<User> findByFullNameStudent(String name);

    @Query("SELECT u FROM User u JOIN u.role rl WHERE rl.role = 'TEACHER' AND u.fullName LIKE %:teacherName% order by u.startDate desc")
    Page<User> teacherSearch(Pageable pageable, @Param("teacherName") String teacherName);

    @Query("SELECT new com.example.managercourse.dto.response.StudentSearchResponse(u.fullName, u.gender, " +
            "u.yearOfBirth, u.startDate, u.phoneNumber, u.email, u.address, cs.courseName) " +
            "FROM User u JOIN u.role rl JOIN u.courseDetailList cd JOIN cd.course cs " +
            "WHERE rl.role = 'STUDENT' AND u.fullName LIKE %:studentName% order by u.startDate desc")
    Page<StudentSearchResponse> searchStudent(Pageable pageable,@Param("studentName") String studentName);

    @Query("SELECT new com.example.managercourse.dto.response.TeacherOfClassResponse(u.id, u.fullName) " +
            "FROM User u JOIN u.role rl WHERE rl.role = 'TEACHER'")
    List<TeacherOfClassResponse> fillTeacher();

    @Query("SELECT new com.example.managercourse.dto.response.StudentCbResponse(s.fullName) FROM User s JOIN s.role rl " +
            "WHERE rl.role = 'STUDENT'")
    List<StudentCbResponse> comboboxStudent();

    @Query("SELECT new com.example.managercourse.dto.response.UserInformationResponse(u.id, u.fullName, u.gender, u.phoneNumber, u.email) " +
            "FROM User u " +
            "JOIN u.role rl " +
            "JOIN u.classDetailList cd " +
            "JOIn cd.aClass cl WHERE cl.id = :id")
    UserInformationResponse teacherInformation(@Param("id") Integer id);

    @Query("SELECT new com.example.managercourse.dto.response.StudentResponse(u.id, u.codeName, u.fullName, u.gender," +
            "u.yearOfBirth, u.startDate, u.phoneNumber, u.email, u.address, cs.courseName, u.status) " +
            "FROM User u JOIN u.role rl JOIN u.courseDetailList cd JOIN cd.course cs " +
            "WHERE rl.role = 'STUDENT' AND u.id = :id")
    StudentResponse studentDetail(Integer id);

    @Query("SELECT new com.example.managercourse.dto.response.StudentCheckBoxResponse(u.id, u.codeName, u.fullName) " +
            "FROM User u " +
            "JOIN u.courseDetailList cd " +
            "JOIN cd.course cs " +
            "JOIN u.role rl " +
            "WHERE cs.courseName = :courseName AND rl.role = 'STUDENT'" +
            "AND NOT EXISTS (SELECT 1 FROM ClassDetail cd JOIN cd.user us WHERE us.id = u.id AND cs.id = cs.id)")
    List<StudentCheckBoxResponse> showListStudent(@Param("courseName") String courseName);

    Integer countUserByRole_Role(String role);

    Optional<User> findByEmail(String email);

}
