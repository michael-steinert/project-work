package de.share_your_idea.user_registration.controller;

import java.util.List;

import de.share_your_idea.user_registration.entity.UserEntity;
import de.share_your_idea.user_registration.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class MainController {

    @Value("${spring.application.name}")
    private String serviceName;
    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        log.info("Main Controller: Index Method is called");
        model.addAttribute("serviceName", serviceName);
        return "index";
    }

    @GetMapping(value = {"/userList"})
    public String personList(Model model) {
        log.info("Main Controller: PersonList Method is called");
        List<UserEntity> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "userList";
    }

    @GetMapping(value = {"/addUser"})
    public String showAddUserPage(Model model) {
        UserEntity userEntity = new UserEntity();
        model.addAttribute("userEntity", userEntity);
        return "addUser";
    }

    @PostMapping(value = {"/addUser"})
    public String saveUser(Model model, @ModelAttribute("userEntity") UserEntity userEntity) {
        log.info("Main Controller: SaveUser Method is called");
        userService.saveUser(userEntity);
        return "addPerson";
    }
}