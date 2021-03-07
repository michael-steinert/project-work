package de.share_your_idea.user_meeting.repository;

import de.share_your_idea.user_meeting.entity.UserMeetingEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/* To trigger the Annotations from UserMeetingEntity the following Property is necessary */
/* For Example @Column(nullable = false) */
@DataJpaTest(properties = "spring.jpa.properties.javax.persistence.validation.mode=none")
class MeetingEntityRepositoryTest {
    @Autowired
    MeetingEntityRepository meetingEntityRepository;

    @Test
    void itShouldFindMeetingEntityByMeetingName() {
        /* Given */
        UserMeetingEntity userMeetingEntity = new UserMeetingEntity(1L, "testMeetingName", "testCommunicationLink", null);
        /* When */
        meetingEntityRepository.save(userMeetingEntity);
        /* Then */
        Optional<UserMeetingEntity> userMeetingEntityOptional = meetingEntityRepository.findMeetingEntityByMeetingName(userMeetingEntity.getMeetingName());
        assertThat(userMeetingEntityOptional).isPresent().hasValueSatisfying(userMeetingEntityFromRepository -> {
            assertThat(userMeetingEntityFromRepository.getMeetingId()).isEqualTo(userMeetingEntity.getMeetingId());
            assertThat(userMeetingEntityFromRepository.getMeetingName()).isEqualTo(userMeetingEntity.getMeetingName());
            assertThat(userMeetingEntityFromRepository.getCommunicationLink()).isEqualTo(userMeetingEntity.getCommunicationLink());
            assertThat(userMeetingEntityFromRepository.getUserEntityList()).isEqualTo(userMeetingEntity.getUserEntityList());
        });
    }

    @Test
    void itShouldDeleteMeetingEntityByMeetingName() {
        /* Given */
        /* When */
        /* Then */
    }
}