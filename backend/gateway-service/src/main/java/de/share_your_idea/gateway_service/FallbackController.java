package de.share_your_idea.gateway_service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/userManagementServiceFallBack")
    public Mono<String> userManagementServiceFallBackMethod() {
        return Mono.just("UserManagement-Service is taking longer than expected to respond or is down.");
    }

    @RequestMapping("/userMeetingServiceFallBack")
    public Mono<String> userMeetingServiceFallBackMethod() {
        return Mono.just("UserMeeting-Service is taking longer than expected to respond or is down.");
    }

    @RequestMapping("/userMeetingSearchServiceFallBack")
    public Mono<String> userMeetingSearchServiceFallBackMethod() {
        return Mono.just("UserMeetingSearch-Service is taking longer than expected to respond or is down.");
    }
}

