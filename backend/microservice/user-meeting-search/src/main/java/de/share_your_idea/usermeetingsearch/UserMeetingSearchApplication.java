package de.share_your_idea.usermeetingsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserMeetingSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserMeetingSearchApplication.class, args);
    }
}
