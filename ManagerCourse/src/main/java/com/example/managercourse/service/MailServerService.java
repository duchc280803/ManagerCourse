package com.example.managercourse.service;

import com.example.managercourse.dto.request.MailServerRequest;
import com.example.managercourse.dto.response.MessageResponse;
import com.example.managercourse.entity.EmailServer;

import java.util.List;

public interface MailServerService {

    boolean sendEmail(Long id);

    List<EmailServer> getAllMailServer();

    MessageResponse createMailServer(MailServerRequest mailServerRequest);

}
