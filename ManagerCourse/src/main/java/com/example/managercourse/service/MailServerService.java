package com.example.managercourse.service;

import com.example.managercourse.entity.EmailServer;

import java.util.List;

public interface MailServerService {

    boolean sendEmail(Long id);

    List<EmailServer> getAllMailServer();
}
