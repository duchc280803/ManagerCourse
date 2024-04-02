package com.example.managercourse.repository;

import com.example.managercourse.dto.response.ScheduleDetailResponse;
import com.example.managercourse.dto.response.ScheduleResponse;
import com.example.managercourse.entity.Class;
import com.example.managercourse.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    @Query("SELECT new com.example.managercourse.dto.response.ScheduleResponse(sc.id, s.subjectName, clr.classRoomName, clr.floor, sc.dateLearn, sc.startDate, sc.endDate) " +
            "FROM Class cl " +
            "JOIN cl.scheduleList sc " +
            "JOIN sc.classRoom clr " +
            "JOIN sc.subject s " +
            "JOIN cl.userTeacher u " +
            "WHERE (:id IS NULL OR cl.id = :id) AND (:idSubject IS NULL OR s.id = :idSubject) AND (:username IS NULL OR u.username = :username)" +
            "ORDER BY sc.id ASC")
    Page<ScheduleResponse> findAllSchedule(@Param("id") Integer id, @Param("idSubject") Integer idSubject, Pageable pageable, @Param("username") String username);

    @Query("SELECT new com.example.managercourse.dto.response.ScheduleResponse(sc.id, s.subjectName, clr.classRoomName, clr.floor, sc.dateLearn, sc.startDate, sc.endDate) " +
            "FROM Class cl " +
            "JOIN cl.scheduleList sc " +
            "JOIN sc.classRoom clr " +
            "JOIN sc.subject s " +
            "WHERE (:id IS NULL OR cl.id = :id) AND (:idSubject IS NULL OR s.id = :idSubject)" +
            "ORDER BY sc.id ASC")
    Page<ScheduleResponse> findAllScheduleForAdmin(@Param("id") Integer id, @Param("idSubject") Integer idSubject, Pageable pageable);

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

    @Query("SELECT s FROM Schedule s JOIN s.aClass cl WHERE cl.id = :id")
    List<Schedule> findByAClassId(Integer id);

    @Query("SELECT s FROM Schedule s JOIN s.aClass cl WHERE s.classRoom.id = :id AND s.day = :day AND ((s.startDate <= :timeStart AND s.endDate >= :timeStart) OR (s.startDate <= :timeEnd AND s.endDate >= :timeEnd) OR (s.startDate >= :timeStart AND s.endDate <= :timeEnd))")
    List<Schedule> check(LocalTime timeStart, LocalTime timeEnd, Integer day, Integer id);

}
