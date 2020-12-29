package de.share_your_idea.user_management.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.share_your_idea.user_management.model.UserEntity;
import de.share_your_idea.user_management.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.UUID;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "{userUid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserEntity> fetchUser(@PathVariable("userUid") UUID userUid) throws JsonProcessingException {
        log.info("User Controller: FetchUser Method is called");
        UserEntity userEntity = userService.getUserByUserUid(userUid);
        log.info("User Controller: FetchUser Method fetched UserEntity : {}", new ObjectMapper().writeValueAsString(userEntity));
        return new ResponseEntity<>(userEntity, HttpStatus.OK);
    }

    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserEntity>> fetchUsers() throws JsonProcessingException {
        log.info("User Controller: FetchUsers Method is called");
        // throw new ApiRequestException("Custom Exception");
        List<UserEntity> userEntityList =  userService.getAllUsers();
        log.info("User Controller: FetchUsers Method fetched List of UserEntities : {}", new ObjectMapper().writeValueAsString(userEntityList));
        return new ResponseEntity<>(userEntityList, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer insertUser(@RequestBody @Valid UserEntity userEntity) throws JsonProcessingException {
        log.info("User Controller: InsertUser Method is called");
        Integer result = userService.insertUser(userEntity);
        log.info("User Controller: InsertUser Method inserted UserEntity : {}", new ObjectMapper().writeValueAsString(result));
        return result;
    }

    @PutMapping(path = "{userUid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer updateUser(@PathVariable("userUid") UUID userUid, @RequestBody @Valid UserEntity userEntity) throws JsonProcessingException {
        log.info("User Controller: UpdateUser Method is called");
        Integer result = userService.updateUserByUserUid(userUid, userEntity);
        log.info("User Controller: InsertUser Method updated UserEntity : {}", new ObjectMapper().writeValueAsString(result));
        return result;
    }

    @DeleteMapping(path = "{userUid}")
    public Integer deleteUser(@PathVariable("userUid") UUID userUid) throws JsonProcessingException {
        log.info("User Controller: DeleteUser Method is called");
        Integer result = userService.removeUser(userUid);
        log.info("User Controller: InsertUser Method deleted UserEntity : {}", new ObjectMapper().writeValueAsString(result));
        return result;
    }

    //Test Endpoint for Admin Area
    @GetMapping(path = "/admin/get")
    public String helloAdmin() {
        return "Hello Admin!";
    }
}
