package de.share_your_idea.user_meeting.service;

import de.share_your_idea.user_meeting.entity.UserMeetingEntity;
import de.share_your_idea.user_meeting.entity.UserEntity;
import de.share_your_idea.user_meeting.repository.MeetingEntityRepository;
import de.share_your_idea.user_meeting.repository.UserEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserMeetingService {
    private final UserEntityRepository userEntityRepository;
    private final MeetingEntityRepository meetingEntityRepository;

    @Autowired
    public UserMeetingService(UserEntityRepository userEntityRepository, MeetingEntityRepository meetingEntityRepository) {
        this.userEntityRepository = userEntityRepository;
        this.meetingEntityRepository = meetingEntityRepository;
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

    public UserEntity findUserByUsername(String username) {
        log.info("User-Meeting-Service: FindUserByUsername-Method is called");
        if (username != null) {
            UserEntity userEntity = userEntityRepository.findUserEntityByUsername(username);
            return userEntity;
        }
        return null;
    }

    public List<UserEntity> findAllUsers() {
        log.info("User-Meeting-Service: FindAllUsers-Method is called");
        return userEntityRepository.findAll();
    }
}
