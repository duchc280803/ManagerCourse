package com.example.managercourse.service.impl;

import com.example.managercourse.dto.request.ContactRequest;
import com.example.managercourse.dto.response.ContactResponse;
import com.example.managercourse.dto.response.MessageResponse;
import com.example.managercourse.entity.Contact;
import com.example.managercourse.repository.ContactRepository;
import com.example.managercourse.service.ContactService;
import com.example.managercourse.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<ContactResponse> selectContact(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Contact> contactResponses = contactRepository.findAll(pageable);
        return MapperUtil.toDTOs(contactResponses.getContent(), ContactResponse.class);
    }

    @Override
    public MessageResponse createContact(ContactRequest contactRequest) {
        Contact contact = Contact
                .builder()
                .email(contactRequest.getEmail())
                .fullName(contactRequest.getFullName())
                .message(contactRequest.getMessage())
                .phoneNumber(contactRequest.getPhoneName())
                .status("Chờ xác nhận")
                .build();
        contactRepository.save(contact);
        return MessageResponse.builder().message("ok").build();
    }
}
