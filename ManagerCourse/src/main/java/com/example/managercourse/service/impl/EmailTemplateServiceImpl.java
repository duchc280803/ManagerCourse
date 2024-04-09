package com.example.managercourse.service.impl;

import com.example.managercourse.dto.response.MessageResponse;
import com.example.managercourse.entity.EmailTemplate;
import com.example.managercourse.exception.NotFoundException;
import com.example.managercourse.repository.EmailTemplateRepository;
import com.example.managercourse.service.EmailTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailTemplateServiceImpl implements EmailTemplateService {

    private final EmailTemplateRepository emailTemplateRepository;

    @Autowired
    public EmailTemplateServiceImpl(EmailTemplateRepository emailTemplateRepository) {
        this.emailTemplateRepository = emailTemplateRepository;
    }

    @Override
    public List<EmailTemplate> getAllEmailTemplate() {
        return emailTemplateRepository.findAll();
    }

    @Override
    public EmailTemplate getEmailTemplate(Long id) {
        return emailTemplateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Email template not found with id: " + id));
    }

    @Override
    public MessageResponse createEmailTemplate(EmailTemplate emailTemplate) {
        EmailTemplate emailTemplateCreate = EmailTemplate
                .builder()
                .content(emailTemplate.getContent())
                .subject(emailTemplate.getSubject())
                .typeTemplate(emailTemplate.getTypeTemplate())
                .build();
        emailTemplateRepository.save(emailTemplateCreate);
        return MessageResponse.builder().message("OKI").build();
    }

    @Override
    public MessageResponse updateMailServer(Long id, EmailTemplate emailTemplate) {
        Optional<EmailTemplate> optionalEmailTemplate = emailTemplateRepository.findById(id);
        if (optionalEmailTemplate.isPresent()) {
            EmailTemplate emailTemplateUpdate = optionalEmailTemplate.get();
            emailTemplateUpdate.setContent(emailTemplate.getContent());
            emailTemplateUpdate.setSubject(emailTemplate.getSubject());
            emailTemplateUpdate.setTypeTemplate(emailTemplate.getTypeTemplate());
            emailTemplateRepository.save(emailTemplateUpdate);
            return MessageResponse.builder().message("OK").build();
        } else {
            throw new NotFoundException("Email template not found with id: " + id);
        }
    }

}
