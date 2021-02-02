package de.share_your_idea.gatewayservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/userRegistrationServiceFallBack")
    public Mono<String> userRegistrationServiceFallBackMethod() {
        return Mono.just("User Registration Service is taking longer than expected to respond or is down.");
    }

    @RequestMapping("/userManagementServiceFallBack")
    public Mono<String> userManagementServiceFallBackMethod() {
        return Mono.just("User Management Service is taking longer than expected to respond or is down.");
    }
}

