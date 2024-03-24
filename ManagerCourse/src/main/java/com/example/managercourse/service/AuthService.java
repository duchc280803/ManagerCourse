package com.example.managercourse.service;

import com.example.managercourse.dto.request.LoginRequest;
import com.example.managercourse.dto.request.PersonalRequest;
import com.example.managercourse.dto.request.RegisterRequest;
import com.example.managercourse.dto.response.MessageResponse;
import com.example.managercourse.dto.response.PersonalResponse;
import com.example.managercourse.dto.response.TokenResponse;

public interface AuthService {

    TokenResponse login(LoginRequest loginRequest);

    MessageResponse register(RegisterRequest registerRequest);

    PersonalResponse fillPersonal(String username);

    MessageResponse updatePersonalInformation(String username, PersonalRequest personalRequest);
}
