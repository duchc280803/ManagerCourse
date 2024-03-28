package com.example.managercourse.repository;

import com.example.managercourse.dto.response.SubjectResponse;
import com.example.managercourse.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    @Query("SELECT new com.example.managercourse.dto.response.SubjectResponse(s.id, s.subjectCode, s.subjectName, s.curriculumContent, s.learningMode, s.numberOfSessions, s.classify) " +
            "FROM Subject s ORDER BY s.id DESC ")
    Page<SubjectResponse> getListSubjectName(Pageable pageable);

    @Query("SELECT new com.example.managercourse.dto.response.SubjectResponse(s.id, s.subjectCode, s.subjectName, s.curriculumContent, s.learningMode, s.numberOfSessions , s.classify) " +
            "FROM Subject s " +
            "JOIN s.subjectDetailList sd " +
            "JOIN sd.course cs WHERE cs.id = :id")
    Page<SubjectResponse> getListSubjectByCourse(Pageable pageable, @Param("id") Integer id);

    @Query("SELECT new com.example.managercourse.dto.response.SubjectResponse(s.id, s.subjectCode, s.subjectName, s.curriculumContent, s.learningMode, s.numberOfSessions , s.classify) " +
            "FROM Subject s " +
            "WHERE s.subjectName LIKE %:subjectName%")
    Page<SubjectResponse> searchListSubject(Pageable pageable, @Param("subjectName") String subjectName);

    @Query("SELECT new com.example.managercourse.dto.response.SubjectResponse(s.id, s.subjectCode, s.subjectName, s.curriculumContent, s.learningMode , s.numberOfSessions, s.classify) " +
            "FROM Course cs " +
            "JOIN cs.subjectDetailList sd " +
            "JOIN sd.subject s " +
            "WHERE cs.courseName = :courseName")
    List<SubjectResponse> getListSubjectByCourse(@Param("courseName") String courseName);

    @Query("SELECT new com.example.managercourse.dto.response.SubjectResponse(s.id, s.subjectCode, s.subjectName, s.curriculumContent, s.learningMode, s.numberOfSessions, s.classify) " +
            "FROM Class cl " +
            "JOIN cl.course cs " +
            "JOIN cs.subjectDetailList sd " +
            "JOIN sd.subject s " +
            "WHERE cl.id = :id")
    List<SubjectResponse> getListSubjectByClass(@Param("id") Integer id);

    @Query("SELECT new com.example.managercourse.dto.response.SubjectResponse(s.id, s.subjectName) " +
            "FROM Class cl " +
            "JOIN cl.course cs " +
            "JOIN cs.subjectDetailList cd " +
            "JOIn cd.subject s " +
            "WHERE cl.id = :id")
    List<SubjectResponse> getListSubjectName(@Param("id") Integer id);

    @Query("SELECT new com.example.managercourse.dto.response.SubjectResponse(s.id, s.subjectName) " +
            "FROM Subject s WHERE NOT EXISTS " +
            "(SELECT 1 FROM Subject s2 JOIN s2.subjectDetailList sd JOIN sd.course cs WHERE cs.id = :id AND s.id = s2.id)")
    List<SubjectResponse> getListSubjectAddCourse(@Param("id") Integer id);

    @Query("SELECT new com.example.managercourse.dto.response.SubjectResponse(s.id, s.subjectCode, s.subjectName, s.curriculumContent, s.learningMode, s.numberOfSessions, s.classify) " +
            "FROM Class cl " +
            "JOIN cl.course cs " +
            "JOIN cs.subjectDetailList sd " +
            "JOIN sd.subject s " +
            "WHERE cl.id = :id")
    List<SubjectResponse> getListSubjectByClass1(@Param("id") Integer id);

}
