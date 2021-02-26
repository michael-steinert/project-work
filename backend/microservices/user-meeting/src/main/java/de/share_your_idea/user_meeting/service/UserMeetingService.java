package de.share_your_idea.user_meeting.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.share_your_idea.user_meeting.entity.UserMeetingEntity;
import de.share_your_idea.user_meeting.entity.UserEntity;
import de.share_your_idea.user_meeting.exception.CustomNotFoundException;
import de.share_your_idea.user_meeting.repository.MeetingEntityRepository;
import de.share_your_idea.user_meeting.repository.UserEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class UserMeetingService {
    private final UserEntityRepository userEntityRepository;
    private final MeetingEntityRepository meetingEntityRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public UserMeetingService(UserEntityRepository userEntityRepository,
                              MeetingEntityRepository meetingEntityRepository,
                              RestTemplate restTemplate) {
        this.userEntityRepository = userEntityRepository;
        this.meetingEntityRepository = meetingEntityRepository;
        this.restTemplate = restTemplate;
    }

    public UserMeetingEntity saveMeeting(UserMeetingEntity userMeetingEntity) {
        log.info("User-Meeting-Service: SaveMeeting-Method is called");
        return meetingEntityRepository.save(userMeetingEntity);
    }

    public UserMeetingEntity findMeetingByMeetingName(String meetingName) {
        log.info("User-Meeting-Service: FindMeetingByMeetingName-Method is called");
        if (!meetingName.isBlank()) {
            UserMeetingEntity userMeetingEntity = meetingEntityRepository.findMeetingEntityByMeetingName(meetingName);
            return userMeetingEntity;
        }
        return null;
    }

    public List<UserMeetingEntity> findAllMeetings() {
        log.info("User-Meeting-Service: FindAllMeetings-Method is called");
        return meetingEntityRepository.findAll();
    }

    public int deleteMeetingByMeetingName(String meetingName) {
        log.info("User-Meeting-Service: DeleteMeetingByMeetingName-Method is called");
        return meetingEntityRepository.deleteMeetingEntityByMeetingName(meetingName);
    }

    public UserEntity saveUser(UserEntity userEntity) {
        log.info("User-Meeting-Service: SaveUser-Method is called");
        return userEntityRepository.save(userEntity);
    }

    public UserEntity findUserByUsername(String username) throws JsonProcessingException, CustomNotFoundException {
        log.info("User-Meeting-Search-Service: FindUserByUsername-Method is called");
        if (username != null) {
            String resourceUrl = "http://USER-MANAGEMENT-SERVICE/user-management/fetch-user-by-username/";
            ResponseEntity<UserEntity> responseEntity = restTemplate.getForEntity(resourceUrl + username, UserEntity.class);

            if (responseEntity != null && responseEntity.hasBody()) {
                UserEntity userEntity = responseEntity.getBody();
                Long result = userEntityRepository.deleteUserEntityByUsername(userEntity.getUsername());
                log.info("User-Meeting-Search-Service: FindUserByUsername-Method deleted UserEntity with Result : {}", new ObjectMapper().writeValueAsString(result));

                userEntityRepository.save(userEntity);
                return userEntity;
            }
        }
        throw new CustomNotFoundException(String.format("UserEntity with Username %s not found.", username));
    }

    public List<UserEntity> findAllUsers() {
        log.info("User-Meeting-Service: FindAllUsers-Method is called");
        return userEntityRepository.findAll();
    }
}
