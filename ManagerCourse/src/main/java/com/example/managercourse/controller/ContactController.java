package com.example.managercourse.controller;

import com.example.managercourse.dto.request.ContactRequest;
import com.example.managercourse.dto.response.ContactResponse;
import com.example.managercourse.dto.response.MessageResponse;
import com.example.managercourse.service.impl.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contact/")
public class ContactController {

    @Autowired
    private ContactServiceImpl contactService;

    @GetMapping("show")
    public ResponseEntity<List<ContactResponse>> selectContact(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        return new ResponseEntity<>(contactService.selectContact(pageNumber, pageSize), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<MessageResponse> createContact(
            @RequestBody ContactRequest contactRequest
    ) {
        return new ResponseEntity<>(contactService.createContact(contactRequest), HttpStatus.CREATED);
    }

}
