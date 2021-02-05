package de.share_your_idea.usermeeting.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
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
public class MeetingEntity {

    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
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
    private String meetingName;

    @JsonIgnore
    @OneToMany(
            targetEntity = UserEntity.class,
            mappedBy = "meetingEntity",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<UserEntity> userEntityList = new ArrayList<>();

    public MeetingEntity(String meetingName) {
        this.meetingName = meetingName;
    }

    public List<UserEntity> getUserEntityList() {
        return userEntityList;
    }

    public void addUserEntity(UserEntity userEntity) {
        if (!this.userEntityList.contains(userEntity)) {
            this.userEntityList.add(userEntity);
            userEntity.setMeetingEntity(this);
        }
    }

    public void removeUserEntity(UserEntity userEntity) {
        if (this.userEntityList.contains(userEntity)) {
            this.userEntityList.remove(userEntity);
            userEntity.setMeetingEntity(null);
        }
    }
}
