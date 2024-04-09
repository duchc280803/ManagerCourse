package com.example.managercourse.service.impl;

import com.example.managercourse.dto.request.LoginRequest;
import com.example.managercourse.dto.request.PersonalRequest;
import com.example.managercourse.dto.request.RegisterRequest;
import com.example.managercourse.dto.response.MessageResponse;
import com.example.managercourse.dto.response.PersonalResponse;
import com.example.managercourse.dto.response.TokenResponse;
import com.example.managercourse.entity.Role;
import com.example.managercourse.entity.User;
import com.example.managercourse.filter.JwtService;
import com.example.managercourse.model.UserCustomDetail;
import com.example.managercourse.repository.RoleRepository;
import com.example.managercourse.repository.UserRepository;
import com.example.managercourse.service.AuthService;
import com.example.managercourse.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final RoleRepository roleRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtService jwtService,
                           AuthenticationManager authenticationManager,
                           RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
    }

    /**
     * Login
     * @param loginRequest
     * @return token
     */
    @Override
    public TokenResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        Optional<User> userOptional = userRepository.findByUsername(loginRequest.getUsername());
        if (userOptional.isPresent()) {
            String token = jwtService.generateToken(new UserCustomDetail(userOptional.get()));
            return TokenResponse
                    .builder()
                    .accessToken(token)
                    .username(userOptional.get().getUsername()).role(userOptional.get().getRole().getRoleName())
                    .message("Login success").build();
        }
        return TokenResponse.builder().accessToken("Token null").message("Login fails").build();
    }

    /**
     * Register
     * @param registerRequest
     * @return message
     */
    @Override
    public MessageResponse register(RegisterRequest registerRequest) {
        Role role = roleRepository.findByRoleName("STUDENT");
        User user = User
                .builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .role(role)
                .build();
        userRepository.save(user);
        return MessageResponse.builder().message("Đăng ký thành công").build();
    }

    /**
     * Show Personal Information
     *
     * @param username
     * @return object
     */
    @Override
    public PersonalResponse fillPersonal(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            return MapperUtil.toDTO(userOptional.get(), PersonalResponse.class);
        } else {
            throw new UsernameNotFoundException("User not found for username: " + username);
        }
    }

    /**
     * Update Personal Information
     *
     * @param username
     * @param personalRequest
     * @return message
     */
    @Override
    public MessageResponse updatePersonalInformation(String username, PersonalRequest personalRequest) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setFullName(personalRequest.getFullName());
            user.setEmail(personalRequest.getEmail());
            user.setPhoneNumber(personalRequest.getPhoneNumber());
            user.setGender(personalRequest.getGender());
            user.setYearOfBirth(personalRequest.getDateOfBirth());
            user.setAddress(personalRequest.getAddress());
            userRepository.save(user);
            return MessageResponse.builder().message("Sửa thành công").build();
        } else {
            // Handle case when user is not found
            throw new UsernameNotFoundException("User not found for username: " + username);
        }
    }


    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
