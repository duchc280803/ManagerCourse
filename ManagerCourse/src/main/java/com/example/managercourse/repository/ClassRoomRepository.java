package com.example.managercourse.repository;

import com.example.managercourse.dto.response.ClassRoomResponse;
import com.example.managercourse.entity.ClassRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRoomRepository extends JpaRepository<ClassRoom, Integer> {

    @Query("SELECT new com.example.managercourse.dto.response.ClassRoomResponse(" +
            "cr.id, cr.codeClassRoom, cr.classRoomName, cr.typeClassRoom, cr.quantityStudent, cr.floor, cr.status) " +
            "FROM ClassRoom cr WHERE cr.classRoomName LIKE %:classRoomName%")
    Page<ClassRoomResponse> searchClassRoom(@Param("classRoomName") String classRoomName, Pageable pageable);

    @Query("SELECT new com.example.managercourse.dto.response.ClassRoomResponse(cl.id, cl.classRoomName) FROM ClassRoom cl")
    List<ClassRoomResponse> findALlClassRoomName();
}
