package com.example.managercourse.repository;

import com.example.managercourse.entity.ClassDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassDetailRepository extends JpaRepository<ClassDetail, Integer> {
}
