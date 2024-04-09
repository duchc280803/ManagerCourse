package com.example.managercourse.service;

import com.example.managercourse.dto.request.ClassRoomRequest;
import com.example.managercourse.dto.response.ClassRoomResponse;
import com.example.managercourse.dto.response.MessageResponse;

import java.util.List;

public interface ClassRoomService {

    List<ClassRoomResponse> findAllClassRoom(Integer pageNumber, Integer pageSize);

    List<ClassRoomResponse> searchClassRoom(Integer pageNumber, Integer pageSize, String classRoomName);

    ClassRoomResponse detail(Integer id);

    MessageResponse createClassRoom(ClassRoomRequest classRoomRequest);

    MessageResponse updateClassRoom(Integer id, ClassRoomRequest classRoomRequest);

    List<ClassRoomResponse> findALlClassRoomName();
}
