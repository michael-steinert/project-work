package de.share_your_idea.user_meeting.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "MeetingEntity")
@Table(
        name = "meeting_entity",
        uniqueConstraints = {
                @UniqueConstraint(name = "meeting_name_unique", columnNames = "meeting_name")
        }
)
@Data
@NoArgsConstructor
@ToString
public class UserMeetingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "meeting_id",
            updatable = false
    )
    private Long meetingId;

    @Column(
            name = "meeting_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotBlank(message = "MeetingName must be not empty")
    private String meetingName;

    @Column(
            name = "communication_link",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @NotBlank(message = "CommunicationLink must be not empty")
    private String communicationLink;

    @JsonIgnore
    @OneToMany(
            targetEntity = UserEntity.class,
            mappedBy = "userMeetingEntity",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<UserEntity> userEntityList = new ArrayList<>();

    public UserMeetingEntity(String meetingName) {
        this.meetingName = meetingName;
    }

    public List<UserEntity> getUserEntityList() {
        return userEntityList;
    }

    public void addUserEntity(UserEntity userEntity) {
        if (!this.userEntityList.contains(userEntity)) {
            this.userEntityList.add(userEntity);
            userEntity.setUserMeetingEntity(this);
        }
    }

    public void removeUserEntity(UserEntity userEntity) {
        if (this.userEntityList.contains(userEntity)) {
            this.userEntityList.remove(userEntity);
            userEntity.setUserMeetingEntity(null);
        }
    }
}
