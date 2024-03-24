package com.example.managercourse.repository;

import com.example.managercourse.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDetailRepository extends JpaRepository<CourseDetail, Integer> {

    CourseDetail findByCourseAndUser(Course course, User user);

    @Query("SELECT cd FROM SubjectDetail cd WHERE cd.course.id = :id")
    List<SubjectDetail> count(@Param("id") Integer id);
}
