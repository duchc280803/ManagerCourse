package com.example.managercourse.repository;

import com.example.managercourse.dto.response.*;
import com.example.managercourse.entity.Class;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer> {

    @Query("SELECT new com.example.managercourse.dto.response.ClassResponse(cl.id, cl.classCode, cl.className, cl.quantityStudent, s.courseName, u.fullName, cl.status) " +
            "FROM Class cl JOIN cl.course s JOIN cl.userTeacher u JOIN u.role rl WHERE rl.role = 'TEACHER' ORDER BY cl.id desc")
    Page<ClassResponse> findAllClass(Pageable pageable);

    @Query("SELECT new com.example.managercourse.dto.response.ClassResponse(cl.id, cl.classCode, cl.className, cl.quantityStudent, s.courseName, u.fullName, cl.status) " +
            "FROM Class cl " +
            "JOIN cl.course s " +
            "JOIN cl.userTeacher u " +
            "JOIN u.role rl " +
            "WHERE rl.role = 'TEACHER' AND cl.className LIKE %:className% " +
            "ORDER BY cl.id desc")
    Page<ClassResponse> searchClass(Pageable pageable,@Param("className") String className);

    @Query("SELECT new com.example.managercourse.dto.response.UserInformationResponse(u.id, u.fullName, u.gender, u.phoneNumber, u.email) " +
            "FROM Class cl JOIN cl.classDetailList cd JOIN cd.user u JOIN u.role rl " +
            "WHERE cl.id = :id")
    Page<UserInformationResponse> listStudentInformation(Pageable pageable, @Param("id") Integer id);

    @Query("SELECT new com.example.managercourse.dto.response.ClassDetailResponse(cl.id, cl.classCode, cl.className, cl.requirements, cs.courseName, u.fullName, cl.quantityStudent, cl.status) " +
            "FROM Class cl JOIN cl.course cs JOIN cl.userTeacher u JOIN u.role rl " +
            "WHERE cl.id = :id AND rl.role = 'TEACHER'")
    ClassDetailResponse detailClass(Integer id);

    @Query("SELECT new com.example.managercourse.dto.response.CourseOfStudent(s.subjectCode, s.subjectName, u.fullName, s.learningMode, s.classify, s.numberOfSessions) " +
            "FROM CourseDetail cd " +
            "JOIN cd.course cs " +
            "JOIN cd.user u " +
            "JOIN cs.subjectDetailList sd JOIN sd.subject s " +
            "WHERE u.username = :username AND cs.courseName = :courseName")
    Page<CourseOfStudent> getListCourseOfStudent(@Param("username") String username, @Param("courseName") String courseName, Pageable pageable);

    @Query("SELECT new com.example.managercourse.dto.response.ClassResponse(cl.id, cl.className) FROM Class cl")
    List<ClassResponse> findAllClassName();

    @Query("SELECT new com.example.managercourse.dto.response.ClassResponse(cl.id, cl.className) " +
            "FROM Class cl " +
            "JOIN cl.classDetailList cld " +
            "JOIN cld.user u WHERE u.username = :username")
    List<ClassResponse> findAllClassNameForStudent(@Param("username") String username);

    @Query("SELECT new com.example.managercourse.dto.response.ClassResponse(cl.id, cl.className) " +
            "FROM Class cl " +
            "JOIN cl.userTeacher u WHERE u.username = :username")
    List<ClassResponse> findAllClassNameForTeacher(@Param("username") String username);

    @Query("SELECT new com.example.managercourse.dto.response.ClassResponse(cl.id, cl.className) " +
            "FROM Class cl " +
            "JOIN cl.classDetailList cdl " +
            "JOIN cdl.user u " +
            "JOIN u.role rl " +
            "WHERE rl.role = 'STUDENT' AND u.username = :username")
    List<ClassResponse> findAllClassNameOfStudent(@Param("username") String username);

    @Query("SELECT new com.example.managercourse.dto.response.StudentOfTeacherResponse(u.fullName, u.phoneNumber, u.email, u.gender, u.address) " +
            "FROM Class cl " +
            "JOIN cl.classDetailList cdl " +
            "JOIN cdl.user u " +
            "JOIN u.role rl " +
            "WHERE rl.role = 'STUDENT' AND cl.className = :className")
    List<StudentOfTeacherResponse> selectStudentOfTeacher(@Param("className") String className);

}
