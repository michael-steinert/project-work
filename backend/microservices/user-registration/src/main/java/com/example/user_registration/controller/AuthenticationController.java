package com.example.user_registration.controller;

import com.example.user_registration.config.jwt.JwtProvider;
import com.example.user_registration.entity.AuthenticationRequest;
import com.example.user_registration.entity.AuthenticationResponse;
import com.example.user_registration.entity.RegistrationRequest;
import com.example.user_registration.entity.UserEntity;
import com.example.user_registration.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
public class AuthenticationController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    @Autowired
    public AuthenticationController(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        log.info("AuthenticationController: RegisterUser Method is called");
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registrationRequest.getUsername());
        userEntity.setPassword(registrationRequest.getPassword());
        userService.saveUser(userEntity);
        return new ResponseEntity<>("Registration successful", HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest authenticationRequest) {
        log.info("AuthenticationController: Authentication Method is called");
        UserEntity userEntity = userService.findByUsernameAndPassword(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        String token = jwtProvider.generateToken(userEntity.getUsername());
        return new ResponseEntity<>(new AuthenticationResponse(token), HttpStatus.OK);
    }
}