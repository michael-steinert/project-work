package de.share_your_idea.user_management.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.share_your_idea.user_management.security.jwt.JwtProvider;
import de.share_your_idea.user_management.entity.UserEntity;
import de.share_your_idea.user_management.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequestMapping("user-management")
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
    public ResponseEntity<UserEntity> registerUser(@RequestBody @Valid UserEntity userEntity) throws JsonProcessingException {
        log.info("User-Controller: RegisterUser-Method is called");
        UserEntity savedUserEntity = userService.saveUser(userEntity);
        log.info("User-Controller: RegisterUser-Method created UserEntity : {}", new ObjectMapper().writeValueAsString(userEntity));
        log.info("User-Controller: RegisterUser-Method saved UserEntity : {}", new ObjectMapper().writeValueAsString(savedUserEntity));
        return new ResponseEntity<>(savedUserEntity, HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<UserEntity> authentication(@RequestBody UserEntity userEntity) throws JsonProcessingException {
        log.info("User-Controller: Authentication-Method is called");
        UserEntity foundUserEntity = userService.findByUsernameAndPassword(userEntity.getUsername(), userEntity.getPassword());
        String token = jwtProvider.generateToken(foundUserEntity.getUsername());
        foundUserEntity.setAuthorizationToken(token);
        userService.saveUser(foundUserEntity);
        log.info("User-Controller: Authentication-Method created UserEntity : {}", new ObjectMapper().writeValueAsString(foundUserEntity));
        return new ResponseEntity<>(foundUserEntity, HttpStatus.OK);
    }

    @GetMapping(path = "/fetch-user-by-username/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserEntity> fetchUserByUsername(@PathVariable("username") String username) throws JsonProcessingException {
        log.info("User-Controller: FetchUserByUsername-Method is called");
        UserEntity userEntity = userService.findByUsername(username);
        log.info("User-Controller: FetchUserByUsername-Method created UserEntity : {}", new ObjectMapper().writeValueAsString(userEntity));
        return new ResponseEntity<>(userEntity, HttpStatus.OK);
    }

    @GetMapping(path = {"/fetch-all-users"})
    public ResponseEntity<List<UserEntity>> fetchAllUsers() throws JsonProcessingException {
        log.info("User-Controller: FetchAllUsers-Method is called");
        List<UserEntity> userEntityList = userService.findAllUsers();
        log.info("User-Controller: FetchAllUsers-Method created UserEntityList : {}", new ObjectMapper().writeValueAsString(userEntityList));
        return new ResponseEntity<>(userEntityList, HttpStatus.OK);
    }
}