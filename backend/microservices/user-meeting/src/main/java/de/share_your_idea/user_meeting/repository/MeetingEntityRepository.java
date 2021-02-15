package de.share_your_idea.user_meeting.repository;

import de.share_your_idea.user_meeting.entity.UserMeetingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface MeetingEntityRepository extends JpaRepository<UserMeetingEntity, Long> {
    UserMeetingEntity findMeetingEntityByMeetingName(String meetingName);

    @Modifying
    @Transactional(readOnly = false)
    int deleteMeetingEntityByMeetingName(String meetingName);
}
