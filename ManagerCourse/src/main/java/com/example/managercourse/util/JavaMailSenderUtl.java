package com.example.managercourse.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class JavaMailSenderUtl {

    public static void send(String to, String subject, String content, JavaMailSenderImpl javaMailSender, String email, String password) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        try {
            helper.setTo(to);
            helper.setSubject(subject);
            String msg = content;
            helper.setText(msg, true);
            javaMailSender.setUsername(email);
            javaMailSender.setPassword(password);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
