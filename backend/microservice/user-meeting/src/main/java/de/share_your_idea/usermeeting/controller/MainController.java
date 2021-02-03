package de.share_your_idea.usermeeting.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class MainController {

    @Value("${spring.application.name}")
    private String serviceName;

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) throws JsonProcessingException {
        log.info("Main Controller: Index Method is called");
        model.addAttribute("serviceName", serviceName);
        log.info("Main Controller: Index Method created ServiceName : {}", new ObjectMapper().writeValueAsString(serviceName));
        return "index";
    }
}