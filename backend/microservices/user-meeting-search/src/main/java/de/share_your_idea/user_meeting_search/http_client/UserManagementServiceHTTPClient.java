package de.share_your_idea.user_meeting_search.http_client;

import de.share_your_idea.user_meeting_search.entity.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("http://USER-MANAGEMENT-SERVICE/user-management/")
public interface UserManagementServiceHTTPClient {

    @GetMapping("fetch-all-users")
    ResponseEntity<List<UserEntity>> fetchAllUsers();

    @GetMapping("fetch-user-by-username/{username}")
    ResponseEntity<UserEntity> fetchUserByUsername(@PathVariable("username") String username);
}
