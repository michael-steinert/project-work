package com.bike_factory.gatewayservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

    @GetMapping("/customerServiceFallBack")
    public String studentServiceFallBackMethod() {
        return "Customer Service is taking longer than expected";
    }
}

