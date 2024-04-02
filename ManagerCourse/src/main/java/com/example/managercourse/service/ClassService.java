package com.example.managercourse.service;

import com.example.managercourse.dto.request.ClassRequest;
import com.example.managercourse.dto.request.StudentAddClassRequest;
import com.example.managercourse.dto.response.*;
import com.example.managercourse.entity.Class;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClassService {

    List<ClassResponse> getClassResponseList(Integer pageNumber, Integer pageSize);

    List<ClassResponse> searchClass(Integer pageNumber, Integer pageSize, String className);

    MessageResponse createClass(ClassRequest classRequest);

    UserInformationResponse teacherInformation(Integer id);

    List<UserInformationResponse> listStudentInformation(Integer pageNumber, Integer pageSize, Integer id);

    ClassDetailResponse classDetail(Integer id);

    MessageResponse updateClass(Integer id, ClassRequest classRequest);

    MessageResponse addStudentToClass(StudentAddClassRequest studentAddClassRequest, Integer idClass);

    List<ClassResponse> findAllClassName();

    List<ClassResponse> findAllClassNameForStudent(String username);

    List<ClassResponse> findAllClassNameForTeacher(String username);

    List<ClassResponse> findAllClassNameOfStudent(String username);

    List<StudentOfTeacherResponse> selectStudentOfTeacher(String className);

}
