package com.example.managercourse.repository;

import com.example.managercourse.dto.response.PointBySubjectResponse;
import com.example.managercourse.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point, Integer> {

    @Query("SELECT new com.example.managercourse.dto.response.PointBySubjectResponse(s.subjectName, p.pointName) " +
            "FROM Point p " +
            "JOIN p.subject s " +
            "JOIN s.subjectDetailList sd " +
            "JOIN sd.course cs " +
            "JOIN cs.courseDetailList cdl " +
            "JOIN cdl.user u " +
            "where u.username = :username AND cs.courseName = :courseName")
    List<PointBySubjectResponse> selectPointStudent(@Param("username") String username, @Param("courseName") String courseName);

    @Query(value = """
            SELECT u.full_name, COALESCE(p.point, 0) AS point, u.id AS idUser, p.id AS idPoint
            FROM managercourse.class_detail cd
            JOIN managercourse.user u ON cd.user_id = u.id
            JOIN managercourse.class c ON cd.a_class_id = c.id
            LEFT JOIN managercourse.point p ON p.user_id = u.id
            AND p.subject_id = (SELECT id FROM managercourse.subject s WHERE s.subject_name = ?1)
            WHERE c.class_name = ?2
            """, nativeQuery = true)
    List<Object[]> selectPointTeacher(String subjectName, String className);

    @Query("SELECT new com.example.managercourse.dto.response.PointBySubjectResponse(u.fullName, s.subjectName, p.pointName) " +
            "FROM ClassDetail cd " +
            "JOIN cd.aClass cl " +
            "JOIN cd.user u " +
            "JOIN u.pointList p " +
            "JOIN p.subject s WHERE u.id = :id")
    List<PointBySubjectResponse> selectPointForUser(@Param("id") Integer id);
}
