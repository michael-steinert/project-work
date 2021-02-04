package de.share_your_idea.usermeeting.service;

import de.share_your_idea.usermeeting.entity.MeetingEntity;
import de.share_your_idea.usermeeting.entity.UserEntity;
import de.share_your_idea.usermeeting.repository.MeetingEntityRepository;
import de.share_your_idea.usermeeting.repository.UserEntityRepository;
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

    public MeetingEntity saveMeeting(MeetingEntity meetingEntity) {
        log.info("User Meeting Service: SaveMeeting Method is called");
        return meetingEntityRepository.save(meetingEntity);
    }

    public MeetingEntity findMeetingByMeetingName(String meetingName) {
        log.info("User Meeting Service: FindMeetingByMeetingName Method is called");
        if (!meetingName.isBlank()) {
            MeetingEntity meetingEntity = meetingEntityRepository.findMeetingEntityByMeetingName(meetingName);
            return meetingEntity;
        }
        return null;
    }

    public List<MeetingEntity> findAllMeetings() {
        log.info("User Meeting Service: FindAllMeetings Method is called");
        return meetingEntityRepository.findAll();
    }

    public UserEntity saveUser(UserEntity userEntity) {
        log.info("User Meeting Service: SaveUser Method is called");
        return userEntityRepository.save(userEntity);
    }

    public UserEntity findUserByUsername(String username) {
        log.info("User Meeting Service: FindUserByUsername Method is called");
        if (username != null) {
            UserEntity userEntity = userEntityRepository.findUserEntityByUsername(username);
            return userEntity;
        }
        return null;
    }

    public List<UserEntity> findAllUsers() {
        log.info("User Meeting Service: FindAllUsers Method is called");
        return userEntityRepository.findAll();
    }
}
