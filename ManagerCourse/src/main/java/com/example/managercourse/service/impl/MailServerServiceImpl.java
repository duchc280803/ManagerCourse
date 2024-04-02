package com.example.managercourse.service.impl;

import com.example.managercourse.entity.EmailServer;
import com.example.managercourse.repository.MailServerRepository;
import com.example.managercourse.service.MailServerService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

@Service
public class MailServerServiceImpl implements MailServerService {

    @Autowired
    private MailServerRepository mailServerRepository;

    @Override
    public boolean sendEmail(Long id) {
        EmailServer emailServer = mailServerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Email configuration not found"));

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(emailServer.getHost());
        javaMailSender.setPort(emailServer.getPort());
        javaMailSender.setUsername(emailServer.getUsername());
        javaMailSender.setPassword(emailServer.getPassword());
        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", emailServer.getAuth());
        props.put("mail.smtp.starttls.enable", emailServer.getStarttls());

        try {
            javaMailSender.testConnection();
            emailServer.setStatus(1);
            mailServerRepository.save(emailServer);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            emailServer.setStatus(2);
            mailServerRepository.save(emailServer);
            return false; // Trả về false nếu có lỗi xảy ra
        }
    }

    @Override
    public List<EmailServer> getAllMailServer() {
        return mailServerRepository.findAll();
    }

}
