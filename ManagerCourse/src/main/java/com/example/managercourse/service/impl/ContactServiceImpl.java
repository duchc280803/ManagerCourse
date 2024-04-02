package com.example.managercourse.service.impl;

import com.example.managercourse.dto.request.ContactRequest;
import com.example.managercourse.dto.response.ContactResponse;
import com.example.managercourse.dto.response.MessageResponse;
import com.example.managercourse.entity.*;
import com.example.managercourse.enums.StatusContactEnum;
import com.example.managercourse.repository.*;
import com.example.managercourse.service.ContactService;
import com.example.managercourse.util.MapperUtil;
import com.example.managercourse.util.UsernamePasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseDetailRepository courseDetailRepository;

    @Override
    public List<ContactResponse> selectContact(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Contact> contactResponses = contactRepository.selectContact(pageable);
        return MapperUtil.toDTOs(contactResponses.getContent(), ContactResponse.class);
    }

    @Override
    public MessageResponse createContact(ContactRequest contactRequest) {
        Contact contact = Contact
                .builder()
                .email(contactRequest.getEmail())
                .fullName(contactRequest.getFullName())
                .message(contactRequest.getMessage())
                .phoneNumber(contactRequest.getPhoneNumber())
                .status(1)
                .build();
        contactRepository.save(contact);
        return MessageResponse.builder().message("ok").build();
    }

    @Override
    public ContactResponse findByContact(Integer id) {
        return MapperUtil.toDTO(contactRepository.findById(id).get(), ContactResponse.class);
    }

    @Override
    public MessageResponse convertStatusContact(Integer id, Integer status, String course) {
        Contact contact = contactRepository.findById(id).get();
        Course courseName = courseRepository.findByCourseName(course);
        if (status == StatusContactEnum.DANG_XU_LY.getValue()) {
            contact.setStatus(StatusContactEnum.DANG_XU_LY.getValue());
            contactRepository.save(contact);
        } else if (status == (StatusContactEnum.KHONG_NGHE_MAY.getValue())) {
            contact.setStatus(StatusContactEnum.KHONG_NGHE_MAY.getValue());
            contactRepository.save(contact);
        } else if (status == StatusContactEnum.HOC_VIEN.getValue()) {
            Integer count = userRepository.countUserByRole_Role("STUDENT");
            // Tạo người dùng mới cho học viên
            Role role = roleRepository.findByRole("STUDENT");
            User user = new User();
            user.setCodeName("HOC_VIEN_" + count);
            user.setFullName(contact.getFullName());
            user.setEmail(contact.getEmail());
            user.setPhoneNumber(contact.getPhoneNumber());
            user.setUsername(UsernamePasswordGenerator.generateUsername(contact.getFullName()));
            user.setPassword(passwordEncoder.encode(UsernamePasswordGenerator.generatePassword()));
            user.setRole(role);
            userRepository.save(user);
            // Save courseDetail
            CourseDetail courseDetail = new CourseDetail();
            courseDetail.setCourse(courseName);
            courseDetail.setUser(user);
            courseDetailRepository.save(courseDetail);
            // Update new status for contact
            contact.setStatus(4);
            contactRepository.save(contact);
        }
        return MessageResponse.builder().message("Thành công").build();
    }

}
