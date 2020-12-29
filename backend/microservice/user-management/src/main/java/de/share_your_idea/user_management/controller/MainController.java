package de.share_your_idea.user_management.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.share_your_idea.user_management.model.UserEntity;
import de.share_your_idea.user_management.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String index(Model model) throws JsonProcessingException {
        log.info("Main Controller: Index Method is called");
        model.addAttribute("serviceName", serviceName);
        log.info("Main Controller: Index Method created ServiceName : {}", new ObjectMapper().writeValueAsString(serviceName));
        return "index";
    }

    @GetMapping(value = {"/userList"})
    public String userList(Model model) throws JsonProcessingException {
        log.info("Main Controller: PersonList Method is called");
        List<UserEntity> users = userService.getAllUsers();
        model.addAttribute("users", users);
        log.info("Main Controller: Index Method created ServiceName : {}", new ObjectMapper().writeValueAsString(users));
        return "userList";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public String handleException(Model model, Exception exception) throws JsonProcessingException {
        model.addAttribute("exception", exception);
        log.info("Main Controller: HandleException Method threw Exception : {}", new ObjectMapper().writeValueAsString(exception));
        return "handleException";
    }
}