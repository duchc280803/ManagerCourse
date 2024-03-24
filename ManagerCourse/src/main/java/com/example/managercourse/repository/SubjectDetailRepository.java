package com.example.managercourse.repository;

import com.example.managercourse.entity.SubjectDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectDetailRepository extends JpaRepository<SubjectDetail, Integer> {
}
