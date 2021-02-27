package de.share_your_idea.user_meeting.http_client;

import de.share_your_idea.user_meeting.entity.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("http://USER-MANAGEMENT-SERVICE/user-management/")
public interface HTTPClient {

    @GetMapping("fetch-user-by-username/{username}")
    ResponseEntity<UserEntity> fetchUserByUsername(@PathVariable("username") String username);
}
