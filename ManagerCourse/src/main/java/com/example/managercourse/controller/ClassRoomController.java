package com.example.managercourse.controller;

import com.example.managercourse.dto.request.ClassRoomRequest;
import com.example.managercourse.dto.response.ClassRoomResponse;
import com.example.managercourse.dto.response.MessageResponse;
import com.example.managercourse.service.impl.ClassRoomServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/class-room/")
public class ClassRoomController {

    @Autowired
    private ClassRoomServiceImpl classRoomService;

    @GetMapping("show")
    public ResponseEntity<List<ClassRoomResponse>> findAllClassRoom(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize
    ) {
        return new ResponseEntity<>(classRoomService.findAllClassRoom(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("search")
    public ResponseEntity<List<ClassRoomResponse>> searchClassRoom(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "classRoomName") String classRoomName
    ) {
        return new ResponseEntity<>(classRoomService.searchClassRoom(pageNumber, pageSize, classRoomName), HttpStatus.OK);
    }

    @GetMapping("detail")
    public ResponseEntity<ClassRoomResponse> detail(
            @RequestParam(name = "id") Integer id
    ) {
        return new ResponseEntity<>(classRoomService.detail(id), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<MessageResponse> createClassRoom(
            @Valid @RequestBody ClassRoomRequest classRoomRequest
    ) {
        return new ResponseEntity<>(classRoomService.createClassRoom(classRoomRequest), HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<MessageResponse> updateClassRoom(
            @RequestParam(name = "id") Integer id,
            @Valid @RequestBody ClassRoomRequest classRoomRequest
    ) {
        return new ResponseEntity<>(classRoomService.updateClassRoom(id, classRoomRequest), HttpStatus.OK);
    }

    @GetMapping("classroom-name")
    public ResponseEntity<List<ClassRoomResponse>> findALlClassRoomName(
    ) {
        return new ResponseEntity<>(classRoomService.findALlClassRoomName(), HttpStatus.OK);
    }

}
