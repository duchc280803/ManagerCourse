package com.example.managercourse.service.impl;

import com.example.managercourse.dto.response.MessageResponse;
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

    @Override
    public MessageResponse createEmailTemplate(EmailTemplate emailTemplate) {
        EmailTemplate emailTemplateCreate = EmailTemplate
                .builder()
                .content(emailTemplate.getContent())
                .subject(emailTemplate.getSubject())
                .build();
        emailTemplateRepository.save(emailTemplateCreate);
        return MessageResponse.builder().message("OKI").build();
    }

    @Override
    public MessageResponse updateMailServer(Long id, EmailTemplate emailTemplate) {
        EmailTemplate emailTemplateUpdate = emailTemplateRepository.findById(id).get();
        emailTemplateUpdate.setContent(emailTemplate.getContent());
        emailTemplateUpdate.setSubject(emailTemplate.getSubject());
        emailTemplateUpdate.setTypeTemplate(emailTemplate.getTypeTemplate());
        emailTemplateRepository.save(emailTemplateUpdate);
        return MessageResponse.builder().message("OK").build();
    }
}
