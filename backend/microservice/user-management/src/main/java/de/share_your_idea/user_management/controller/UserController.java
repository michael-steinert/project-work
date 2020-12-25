package de.share_your_idea.user_management.controller;

import de.share_your_idea.user_management.model.UserEntity;
import de.share_your_idea.user_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.UUID;

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
        return userService.getUserByUserUid(userUid).orElseThrow(() -> new NotFoundException("User with UserUid " + userUid + " not found."));
    }

    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserEntity> fetchUsers() {
        // throw new ApiRequestException("Custom Exception");
        return userService.getAllUsers().get();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer insertUser(@RequestBody @Valid UserEntity userEntity) {
        return userService.insertUser(userEntity);
    }

    @PutMapping(path = "{userUid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer updateUser(@PathVariable("userUid") UUID userUid, @RequestBody @Valid UserEntity userEntity) {
        return userService.updateUserByUserUid(userUid, userEntity);
    }

    @DeleteMapping(path = "{userUid}")
    public Integer deleteUser(@PathVariable("userUid") UUID userUid) {
        return userService.removeUser(userUid);
    }

    @GetMapping(path = "/admin/get")
    public String helloAdmin() {
        return "Hello Admin!";
    }
}
