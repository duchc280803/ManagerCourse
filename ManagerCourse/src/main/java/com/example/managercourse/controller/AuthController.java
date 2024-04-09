package com.example.managercourse.controller;

import com.example.managercourse.dto.request.LoginRequest;
import com.example.managercourse.dto.request.PersonalRequest;
import com.example.managercourse.dto.request.RegisterRequest;
import com.example.managercourse.dto.response.MessageResponse;
import com.example.managercourse.dto.response.PersonalResponse;
import com.example.managercourse.dto.response.TokenResponse;
import com.example.managercourse.service.impl.AuthServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

    private final AuthServiceImpl authService;

    @Autowired
    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @PostMapping("login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<MessageResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        if (authService.findByUsername(registerRequest.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message("Username already exits").build());
        }
        if (authService.findByEmail(registerRequest.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message("Email already exits").build());
        }
        return new ResponseEntity<>(authService.register(registerRequest), HttpStatus.CREATED);
    }

    @GetMapping("fill-personal")
    public ResponseEntity<PersonalResponse> fillPersonal(@RequestParam(name = "username") String username) {
        return new ResponseEntity<>(authService.fillPersonal(username), HttpStatus.OK);
    }

    @PutMapping("update-personal/{username}")
    public ResponseEntity<MessageResponse> updatePersonal(
            @PathVariable(name = "username") String username,
            @Valid @RequestBody PersonalRequest personalRequest
    ) {
        return new ResponseEntity<>(authService.updatePersonalInformation(username, personalRequest), HttpStatus.OK);
    }
}
