package de.share_your_idea.user_registration.controller;

import de.share_your_idea.user_registration.config.jwt.JwtProvider;
import de.share_your_idea.user_registration.entity.AuthenticationRequest;
import de.share_your_idea.user_registration.entity.AuthenticationResponse;
import de.share_your_idea.user_registration.entity.RegistrationRequest;
import de.share_your_idea.user_registration.entity.UserEntity;
import de.share_your_idea.user_registration.service.UserService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("user-authentication")
@RestController
public class UserController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    @Autowired
    public UserController(UserService userService, JwtProvider jwtProvider) {
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

    @GetMapping(path = "/fetch-user-by-username/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserEntity fetchUserByUsername(@PathVariable("username") String username) {
        return userService.findByUsername(username);
    }
}