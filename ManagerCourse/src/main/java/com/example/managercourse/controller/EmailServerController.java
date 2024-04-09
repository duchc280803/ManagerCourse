package com.example.managercourse.controller;

import com.example.managercourse.dto.request.MailServerRequest;
import com.example.managercourse.dto.response.MessageResponse;
import com.example.managercourse.entity.EmailServer;
import com.example.managercourse.service.impl.MailServerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/email-server/")
public class EmailServerController {

    private final MailServerServiceImpl mailServerService;

    @Autowired
    public EmailServerController(MailServerServiceImpl mailServerService) {
        this.mailServerService = mailServerService;
    }

    @GetMapping("test-connection/{id}")
    public boolean testConnectEmail(@PathVariable("id") Long id) {
        return mailServerService.sendEmail(id);
    }

    @GetMapping("mail-server")
    public ResponseEntity<List<EmailServer>> getAllEmailServer() {
        return new ResponseEntity<>(mailServerService.getAllMailServer(), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<MessageResponse> createMailServer(@RequestBody MailServerRequest mailServerRequest) {
        return new ResponseEntity<>(mailServerService.createMailServer(mailServerRequest), HttpStatus.CREATED);
    }

}
