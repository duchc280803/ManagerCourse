package com.example.managercourse.controller;

import com.example.managercourse.dto.response.MessageResponse;
import com.example.managercourse.entity.EmailTemplate;
import com.example.managercourse.service.impl.EmailTemplateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/templates/")
public class EmailTemplateController {

    private final EmailTemplateServiceImpl emailTemplateService;

    @Autowired
    public EmailTemplateController(EmailTemplateServiceImpl emailTemplateService) {
        this.emailTemplateService = emailTemplateService;
    }

    @GetMapping("show")
    public ResponseEntity<List<EmailTemplate>> getAllEmailTemplate() {
        return new ResponseEntity<>(emailTemplateService.getAllEmailTemplate(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmailTemplate> getEmailTemplate(@PathVariable("id") Long id) {
        return new ResponseEntity<>(emailTemplateService.getEmailTemplate(id), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<MessageResponse> createMailServer(@RequestBody EmailTemplate emailTemplate) {
        return new ResponseEntity<>(emailTemplateService.createEmailTemplate(emailTemplate), HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity<MessageResponse> updateMailServer(@RequestParam("id") Long id, @RequestBody EmailTemplate emailTemplate) {
        return new ResponseEntity<>(emailTemplateService.updateMailServer(id, emailTemplate), HttpStatus.CREATED);
    }
}
