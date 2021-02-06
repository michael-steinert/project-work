package de.share_your_idea.usermeetingsearch.repository;

import de.share_your_idea.usermeetingsearch.entity.UserMeetingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserMeetingEntityRepository extends JpaRepository<UserMeetingEntity, Long> {
    List<UserMeetingEntity> findUserMeetingEntityByMeetingNameContaining(String meetingName);
}
