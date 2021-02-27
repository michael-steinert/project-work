package de.share_your_idea.user_meeting_search.http_client;

import de.share_your_idea.user_meeting_search.entity.UserMeetingEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("http://USER-MEETING-SERVICE/user-meeting/")
public interface UserMeetingServiceHTTPClient {

    @GetMapping("fetch-all-user-meetings")
    ResponseEntity<List<UserMeetingEntity>> fetchAllUserMeetings();
}
