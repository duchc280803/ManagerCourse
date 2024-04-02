package com.example.managercourse.service;

import com.example.managercourse.entity.EmailTemplate;

import java.util.List;

public interface EmailTemplateService {

    List<EmailTemplate> getAllEmailTemplate();

    EmailTemplate getEmailTemplate(Long id);
}
