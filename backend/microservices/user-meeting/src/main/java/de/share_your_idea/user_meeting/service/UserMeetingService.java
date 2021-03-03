package de.share_your_idea.user_meeting.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.share_your_idea.user_meeting.entity.UserMeetingEntity;
import de.share_your_idea.user_meeting.entity.UserEntity;
import de.share_your_idea.user_meeting.exception.CustomEmptyInputException;
import de.share_your_idea.user_meeting.exception.CustomNotFoundException;
import de.share_your_idea.user_meeting.http_client.UserManagementServiceHTTPClient;
import de.share_your_idea.user_meeting.repository.MeetingEntityRepository;
import de.share_your_idea.user_meeting.repository.UserEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserMeetingService {
    private final UserEntityRepository userEntityRepository;
    private final MeetingEntityRepository meetingEntityRepository;
    private final UserManagementServiceHTTPClient userManagementServiceHttpClient;

    @Autowired
    public UserMeetingService(UserEntityRepository userEntityRepository,
                              MeetingEntityRepository meetingEntityRepository,
                              UserManagementServiceHTTPClient userManagementServiceHttpClient) {
        this.userEntityRepository = userEntityRepository;
        this.meetingEntityRepository = meetingEntityRepository;
        this.userManagementServiceHttpClient = userManagementServiceHttpClient;
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
        if (!username.isBlank()) {
            ResponseEntity<UserEntity> responseEntity = userManagementServiceHttpClient.fetchUserByUsername(username);
            if (responseEntity != null && responseEntity.hasBody()) {
                UserEntity userEntity = responseEntity.getBody();
                UserEntity userEntityFromRepository = userEntityRepository.findUserEntityByUsername(userEntity.getUsername());
                if (!userEntity.equals(userEntityFromRepository)) {
                    /*
                    Server-side Caching:
                    Here the UserEntity is cached to ensure that even if the Microservice UserManagement is not available,
                    the existing UserEntity is returned from the Repository.
                    */
                    Long result = userEntityRepository.deleteUserEntityByUsername(userEntity.getUsername());
                    log.info("User-Meeting-Search-Service: FindUserByUsername-Method deleted UserEntity with Result : {}", new ObjectMapper().writeValueAsString(result));
                    if (result != 0) {
                        userEntityRepository.save(userEntity);
                        userEntity = userEntityRepository.findUserEntityByUsername(userEntity.getUsername());
                    }
                }
                return userEntity;
            }
            throw new CustomNotFoundException(String.format("UserEntity with Username %s not found.", username));
        }
        throw new CustomEmptyInputException("The Username is empty.");
    }

    public List<UserEntity> findAllUsers() {
        log.info("User-Meeting-Service: FindAllUsers-Method is called");
        return userEntityRepository.findAll();
    }
}
