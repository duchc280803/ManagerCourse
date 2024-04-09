package com.example.managercourse.service.impl;

import com.example.managercourse.dto.request.ClassRoomRequest;
import com.example.managercourse.dto.response.ClassRoomResponse;
import com.example.managercourse.dto.response.MessageResponse;
import com.example.managercourse.entity.ClassRoom;
import com.example.managercourse.exception.NotFoundException;
import com.example.managercourse.repository.ClassRoomRepository;
import com.example.managercourse.service.ClassRoomService;
import com.example.managercourse.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClassRoomServiceImpl implements ClassRoomService {

    private final ClassRoomRepository classRoomRepository;

    @Autowired
    public ClassRoomServiceImpl(ClassRoomRepository classRoomRepository) {
        this.classRoomRepository = classRoomRepository;
    }

    @Override
    public List<ClassRoomResponse> findAllClassRoom(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Page<ClassRoom> classRoomPage = classRoomRepository.findAll(pageable);
        return MapperUtil.toDTOs(classRoomPage.getContent(), ClassRoomResponse.class);
    }

    @Override
    public List<ClassRoomResponse> searchClassRoom(Integer pageNumber, Integer pageSize, String classRoomName) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<ClassRoomResponse> classRoomPage = classRoomRepository.searchClassRoom(classRoomName, pageable);
        return classRoomPage.getContent();
    }

    @Override
    public ClassRoomResponse detail(Integer id) {
        Optional<ClassRoom> classRoomOptional = classRoomRepository.findById(id);
        if (classRoomOptional.isPresent()) {
            ClassRoom classRoom = classRoomOptional.get();
            return MapperUtil.toDTO(classRoom, ClassRoomResponse.class);
        } else {
            // Handle case when class room is not found
            throw new NotFoundException("Class room not found for id: " + id);
        }
    }


    @Override
    @Transactional
    public MessageResponse createClassRoom(ClassRoomRequest classRoomRequest) {
        ClassRoom classRoom = ClassRoom
                .builder()
                .classRoomName(classRoomRequest.getClassRoomName())
                .codeClassRoom(classRoomRequest.getCodeClassRoom())
                .floor(classRoomRequest.getFloor())
                .quantityStudent(classRoomRequest.getQuantityStudent())
                .status(classRoomRequest.getStatus())
                .typeClassRoom(classRoomRequest.getTypeClassRoom())
                .build();
        classRoomRepository.save(classRoom);
        return MessageResponse.builder().message("Thêm thành công").build();
    }

    @Override
    @Transactional
    public MessageResponse updateClassRoom(Integer id, ClassRoomRequest classRoomRequest) {
        Optional<ClassRoom> classRoomOptional = classRoomRepository.findById(id);
        classRoomOptional.ifPresent(classRoom -> {
            classRoom.setClassRoomName(classRoomRequest.getClassRoomName());
            classRoom.setCodeClassRoom(classRoomRequest.getCodeClassRoom());
            classRoom.setQuantityStudent(classRoomRequest.getQuantityStudent());
            classRoom.setStatus(classRoomRequest.getStatus());
            classRoom.setTypeClassRoom(classRoomRequest.getTypeClassRoom());
            classRoom.setFloor(classRoom.getFloor());
            classRoomRepository.save(classRoom);
        });
        return MessageResponse.builder().message("Update thành công").build();
    }

    @Override
    public List<ClassRoomResponse> findALlClassRoomName() {
        return classRoomRepository.findALlClassRoomName();
    }
}
