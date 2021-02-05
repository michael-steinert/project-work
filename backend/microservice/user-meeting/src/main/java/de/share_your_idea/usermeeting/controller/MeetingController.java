package de.share_your_idea.usermeeting.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.share_your_idea.usermeeting.entity.MeetingEntity;
import de.share_your_idea.usermeeting.entity.UserEntity;
import de.share_your_idea.usermeeting.service.UserMeetingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequestMapping("user-meeting")
@RestController
public class MeetingController {

    private final UserMeetingService userMeetingService;

    @Autowired
    public MeetingController(UserMeetingService userMeetingService) {
        this.userMeetingService = userMeetingService;
    }

    @GetMapping("/register-meeting/{meetingName}")
    public ResponseEntity<MeetingEntity> registerMeeting(@PathVariable("meetingName") String meetingName) throws JsonProcessingException {
        log.info("Meeting-Controller: RegisterMeeting-Method is called");
        MeetingEntity meetingEntity = new MeetingEntity(meetingName);
        MeetingEntity savedMeetingEntity = userMeetingService.saveMeeting(meetingEntity);
        log.info("Meeting-Controller: RegisterMeeting-Method saved MeetingEntity : {}", new ObjectMapper().writeValueAsString(savedMeetingEntity));
        return new ResponseEntity<>(savedMeetingEntity, HttpStatus.OK);
    }

    @GetMapping("/unregister-meeting/{meetingName}")
    public ResponseEntity<Integer> unregisterMeeting(@PathVariable("meetingName") String meetingName) throws JsonProcessingException {
        log.info("Meeting-Controller: UnregisterMeeting-Method is called");
        int deleteResult = userMeetingService.deleteMeetingByMeetingName(meetingName);
        log.info("Meeting-Controller: UnregisterMeeting-Method deleted MeetingEntity with Result : {}", new ObjectMapper().writeValueAsString(deleteResult));
        return new ResponseEntity<>(deleteResult, HttpStatus.OK);
    }

    @PostMapping("/register-to-meeting/{meetingName}")
    public ResponseEntity<MeetingEntity> registerUserToMeeting(@PathVariable("meetingName") String meetingName, @RequestBody @Valid UserEntity userEntity) throws JsonProcessingException {
        log.info("Meeting-Controller: RegisterUserToMeeting-Method is called");
        MeetingEntity meetingEntity = userMeetingService.findMeetingByMeetingName(meetingName);
        MeetingEntity savedMeetingEntity = null;
        UserEntity savedUserEntity = null;
        if (meetingEntity != null && userEntity != null) {
            savedUserEntity = userMeetingService.saveUser(userEntity);
            meetingEntity.addUserEntity(savedUserEntity);
            savedMeetingEntity = userMeetingService.saveMeeting(meetingEntity);
        }
        log.info("Meeting-Controller: RegisterUserToMeeting-Method saved UserEntity : {}", new ObjectMapper().writeValueAsString(savedUserEntity));
        log.info("Meeting-Controller: RegisterUserToMeeting-Method saved MeetingEntity : {}", new ObjectMapper().writeValueAsString(savedMeetingEntity));
        return new ResponseEntity<>(savedMeetingEntity, HttpStatus.OK);
    }

    @PostMapping("/unregister-from-meeting/{meetingName}")
    public ResponseEntity<MeetingEntity> unregisterUserFromMeeting(@PathVariable("meetingName") String meetingName, @RequestBody @Valid UserEntity userEntity) throws JsonProcessingException {
        log.info("Meeting-Controller: UnregisterUserFromMeeting-Method is called");
        MeetingEntity meetingEntity = userMeetingService.findMeetingByMeetingName(meetingName);
        UserEntity savedUserEntity = null;
        MeetingEntity savedMeetingEntity = null;
        if (meetingEntity != null) {
            savedUserEntity = userMeetingService.saveUser(userEntity);
            meetingEntity.removeUserEntity(savedUserEntity);
            savedMeetingEntity = userMeetingService.saveMeeting(meetingEntity);
        }
        log.info("Meeting-Controller: UnregisterUserToMeeting-Method saved UserEntity : {}", new ObjectMapper().writeValueAsString(savedUserEntity));
        log.info("Meeting-Controller: UnregisterUserToMeeting-Method saved MeetingEntity : {}", new ObjectMapper().writeValueAsString(savedMeetingEntity));
        return new ResponseEntity<>(savedMeetingEntity, HttpStatus.OK);
    }
}