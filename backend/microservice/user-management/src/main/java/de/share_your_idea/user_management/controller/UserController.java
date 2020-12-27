package de.share_your_idea.user_management.controller;

import de.share_your_idea.user_management.model.UserEntity;
import de.share_your_idea.user_management.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    public UserEntity fetchUser(@PathVariable("userUid") UUID userUid) {
        log.info("User Controller: FetchUser Method is called");
        return userService.getUserByUserUid(userUid).orElseThrow(() -> new NotFoundException("User with UserUid " + userUid + " not found."));
    }

    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserEntity> fetchUsers() {
        log.info("User Controller: FetchUsers Method is called");
        // throw new ApiRequestException("Custom Exception");
        return userService.getAllUsers().get();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer insertUser(@RequestBody @Valid UserEntity userEntity) {
        log.info("User Controller: InsertUser Method is called");
        return userService.insertUser(userEntity);
    }

    @PutMapping(path = "{userUid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer updateUser(@PathVariable("userUid") UUID userUid, @RequestBody @Valid UserEntity userEntity) {
        log.info("User Controller: UpdateUser Method is called");
        return userService.updateUserByUserUid(userUid, userEntity);
    }

    @DeleteMapping(path = "{userUid}")
    public Integer deleteUser(@PathVariable("userUid") UUID userUid) {
        log.info("User Controller: DeleteUser Method is called");
        return userService.removeUser(userUid);
    }

    //Test Endpoint for Admin Area
    @GetMapping(path = "/admin/get")
    public String helloAdmin() {
        return "Hello Admin!";
    }
}
