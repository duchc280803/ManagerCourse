package com.example.managercourse.repository;

import com.example.managercourse.dto.response.PointBySubjectResponse;
import com.example.managercourse.entity.StudyPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyPointRepository extends JpaRepository<StudyPoint, Integer> {

    @Query("SELECT new com.example.managercourse.dto.response.PointBySubjectResponse(p.id, p.pointName, p.weight, p.description, p.status)" +
            "FROM StudyPoint p")
    List<PointBySubjectResponse> findBySubject();

}
