package com.example.managercourse.controller;

import com.example.managercourse.entity.EmailServer;
import com.example.managercourse.service.impl.MailServerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/email-server/")
public class EmailServerController {

    @Autowired
    private MailServerServiceImpl mailServerService;

    @GetMapping("test-connection/{id}")
    public boolean testConnectEmail(@PathVariable("id") Long id) {
        return mailServerService.sendEmail(id);
    }

    @GetMapping("mail-server")
    public ResponseEntity<List<EmailServer>> getAllEmailServer() {
        return new ResponseEntity<>(mailServerService.getAllMailServer(), HttpStatus.OK);
    }
}
