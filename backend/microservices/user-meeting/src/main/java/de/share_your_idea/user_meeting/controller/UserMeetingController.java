package de.share_your_idea.user_meeting.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.share_your_idea.user_meeting.entity.UserMeetingEntity;
import de.share_your_idea.user_meeting.entity.UserEntity;
import de.share_your_idea.user_meeting.service.UserMeetingService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequestMapping("user-meeting")
@RestController
public class UserMeetingController {

    private final UserMeetingService userMeetingService;

    @Autowired
    public UserMeetingController(UserMeetingService userMeetingService) {
        this.userMeetingService = userMeetingService;
    }

    @PostMapping("/register-meeting")
    public ResponseEntity<UserMeetingEntity> registerMeeting(@RequestBody @Valid UserMeetingEntity userMeetingEntity) throws JsonProcessingException {
        log.info("Meeting-Controller: RegisterMeeting-Method is called");
        UserMeetingEntity savedUserMeetingEntity = userMeetingService.saveMeeting(userMeetingEntity);
        log.info("Meeting-Controller: RegisterMeeting-Method saved MeetingEntity : {}", new ObjectMapper().writeValueAsString(savedUserMeetingEntity));
        return new ResponseEntity<>(savedUserMeetingEntity, HttpStatus.OK);
    }

    @GetMapping("/unregister-meeting/{meetingName}")
    public ResponseEntity<Integer> unregisterMeeting(@PathVariable("meetingName") String meetingName) throws JsonProcessingException {
        log.info("Meeting-Controller: UnregisterMeeting-Method is called");
        int deleteResult = userMeetingService.deleteMeetingByMeetingName(meetingName);
        log.info("Meeting-Controller: UnregisterMeeting-Method deleted MeetingEntity with Result : {}", new ObjectMapper().writeValueAsString(deleteResult));
        return new ResponseEntity<>(deleteResult, HttpStatus.OK);
    }

    @PostMapping("/register-to-meeting/{meetingName}")
    public ResponseEntity<UserMeetingEntity> registerUserToMeeting(@PathVariable("meetingName") String meetingName, @RequestBody @Valid UserEntity userEntity) throws JsonProcessingException {
        log.info("Meeting-Controller: RegisterUserToMeeting-Method is called");
        UserMeetingEntity userMeetingEntity = userMeetingService.findMeetingByMeetingName(meetingName);
        UserMeetingEntity savedUserMeetingEntity = null;
        UserEntity savedUserEntity = null;
        if (userMeetingEntity != null && userEntity != null) {
            savedUserEntity = userMeetingService.saveUser(userEntity);
            userMeetingEntity.addUserEntity(savedUserEntity);
            savedUserMeetingEntity = userMeetingService.saveMeeting(userMeetingEntity);
        }
        log.info("Meeting-Controller: RegisterUserToMeeting-Method saved UserEntity : {}", new ObjectMapper().writeValueAsString(savedUserEntity));
        log.info("Meeting-Controller: RegisterUserToMeeting-Method saved MeetingEntity : {}", new ObjectMapper().writeValueAsString(savedUserMeetingEntity));
        return new ResponseEntity<>(savedUserMeetingEntity, HttpStatus.OK);
    }

    @PostMapping("/unregister-from-meeting/{meetingName}")
    public ResponseEntity<UserMeetingEntity> unregisterUserFromMeeting(@PathVariable("meetingName") String meetingName, @RequestBody @Valid UserEntity userEntity) throws JsonProcessingException {
        log.info("Meeting-Controller: UnregisterUserFromMeeting-Method is called");
        UserMeetingEntity userMeetingEntity = userMeetingService.findMeetingByMeetingName(meetingName);
        UserEntity savedUserEntity = null;
        UserMeetingEntity savedUserMeetingEntity = null;
        if (userMeetingEntity != null) {
            savedUserEntity = userMeetingService.saveUser(userEntity);
            userMeetingEntity.removeUserEntity(savedUserEntity);
            savedUserMeetingEntity = userMeetingService.saveMeeting(userMeetingEntity);
        }
        log.info("Meeting-Controller: UnregisterUserToMeeting-Method saved UserEntity : {}", new ObjectMapper().writeValueAsString(savedUserEntity));
        log.info("Meeting-Controller: UnregisterUserToMeeting-Method saved MeetingEntity : {}", new ObjectMapper().writeValueAsString(savedUserMeetingEntity));
        return new ResponseEntity<>(savedUserMeetingEntity, HttpStatus.OK);
    }

    @GetMapping(path = {"/fetch-all-user-meetings"})
    public ResponseEntity<List<UserMeetingEntity>> fetchAllUserMeetings() throws JsonProcessingException {
        log.info("Meeting-Controller: FetchAllUserMeetings-Method is called");
        List<UserMeetingEntity> userMeetingEntityList = userMeetingService.findAllMeetings();
        log.info("Meeting-Controller: FetchAllUserMeetings-Method created UserMeetingEntityList : {}", new ObjectMapper().writeValueAsString(userMeetingEntityList));
        return new ResponseEntity<>(userMeetingEntityList, HttpStatus.OK);
    }

    @GetMapping(path = "/fetch-user-by-username/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserEntity> fetchUserByUsername(@PathVariable("username") String username) throws JsonProcessingException, NotFoundException {
        log.info("Search-Query-Controller: FetchUserByUsername-Method is called");
        UserEntity userEntity = userMeetingService.findUserByUsername(username);
        log.info("Search-Query-Controller: FetchUserByUsername-Method created UserEntity : {}", new ObjectMapper().writeValueAsString(userEntity));
        return new ResponseEntity<>(userEntity, HttpStatus.OK);
    }
}