package com.example.managercourse.repository;

import com.example.managercourse.dto.response.ScheduleDetailResponse;
import com.example.managercourse.dto.response.ScheduleResponse;
import com.example.managercourse.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    @Query("SELECT new com.example.managercourse.dto.response.ScheduleResponse(sc.id, s.subjectName, clr.classRoomName, clr.floor, sc.dateLearn, sc.startDate, sc.endDate) " +
            "FROM Class cl " +
            "JOIN cl.scheduleList sc " +
            "JOIN sc.classRoom clr " +
            "JOIN sc.subject s " +
            "WHERE (:id IS NULL OR cl.id = :id) AND (:subjectName IS NULL OR s.subjectName = :subjectName)" +
            "ORDER BY sc.id ASC ")
    Page<ScheduleResponse> findAllSchedule(@Param("id") Integer id, @Param("subjectName") String subjectName, Pageable pageable);

    @Query("SELECT new com.example.managercourse.dto.response.ScheduleDetailResponse(sc.id, cl.id, s.id, clr.id, sc.day, sc.startDate, sc.endDate) " +
            "FROM Class cl " +
            "JOIN cl.scheduleList sc " +
            "JOIN sc.classRoom clr " +
            "JOIN sc.subject s WHERE sc.id = :id")
    ScheduleDetailResponse detail(@Param("id") Integer id);

    @Query("SELECT new com.example.managercourse.dto.response.ScheduleResponse(sc.id, s.subjectName, clr.classRoomName, clr.floor, sc.dateLearn, sc.startDate, sc.endDate) " +
            "FROM Class cl " +
            "JOIN cl.classDetailList cld " +
            "JOIn cld.user u " +
            "JOIN cl.scheduleList sc " +
            "JOIN sc.classRoom clr " +
            "JOIN sc.subject s " +
            "WHERE cl.id = :id AND u.username = :username " +
            "ORDER BY sc.id ASC ")
    Page<ScheduleResponse> findAllScheduleByCourse(@Param("id") Integer id, Pageable pageable, String username);
}
