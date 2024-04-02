package com.example.managercourse.controller;

import com.example.managercourse.entity.EmailTemplate;
import com.example.managercourse.service.impl.EmailTemplateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/templates/")
public class EmailTemplateController {

    @Autowired
    private EmailTemplateServiceImpl emailTemplateService;

    @GetMapping("show")
    public ResponseEntity<List<EmailTemplate>> getAllEmailTemplate() {
        return new ResponseEntity<>(emailTemplateService.getAllEmailTemplate(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmailTemplate> getEmailTemplate(@PathVariable("id") Long id) {
        return new ResponseEntity<>(emailTemplateService.getEmailTemplate(id), HttpStatus.OK);
    }
}
