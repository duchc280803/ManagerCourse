package com.example.managercourse.service.impl;

import com.example.managercourse.entity.EmailTemplate;
import com.example.managercourse.repository.EmailTemplateRepository;
import com.example.managercourse.service.EmailTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailTemplateServiceImpl implements EmailTemplateService {

    @Autowired
    private EmailTemplateRepository emailTemplateRepository;

    @Override
    public List<EmailTemplate> getAllEmailTemplate() {
        return emailTemplateRepository.findAll();
    }

    @Override
    public EmailTemplate getEmailTemplate(Long id) {
        return emailTemplateRepository.findById(id).get();
    }
}
