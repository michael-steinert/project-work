package de.share_your_idea.usermeetingsearch.repository;

import de.share_your_idea.usermeetingsearch.entity.SearchQueryEntity;
import de.share_your_idea.usermeetingsearch.entity.UserEntity;
import de.share_your_idea.usermeetingsearch.entity.UserMeetingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchQueryEntityRepository extends JpaRepository<SearchQueryEntity, Long> {
    List<UserEntity> findUserEntityByUsernameContaining(String username);

    List<UserMeetingEntity> findUserMeetingEntityByMeetingNameContaining(String meetingName);
}
