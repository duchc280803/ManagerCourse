package com.example.managercourse.service;

import com.example.managercourse.dto.request.ContactRequest;
import com.example.managercourse.dto.response.ContactResponse;
import com.example.managercourse.dto.response.MessageResponse;

import java.util.List;

public interface ContactService {

    List<ContactResponse> selectContact(Integer pageNumber, Integer pageSize);

    MessageResponse createContact(ContactRequest contactRequest);

    ContactResponse findByContact(Integer id);

    MessageResponse convertStatusContact(Integer id, Integer status, String course);
}
