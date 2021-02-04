package de.share_your_idea.usermeeting.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "MeetingEntity")
@Table(
        name = "meeting_entity",
        uniqueConstraints = {
                @UniqueConstraint(name = "username_unique", columnNames = "meeting_name")
        }
)
@Data
@NoArgsConstructor
@ToString
public class MeetingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "meeting_id",
            updatable = false
    )
    private UUID meeting_id;

    @Column(
            name = "meeting_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String meetingName;

    @OneToMany(
            mappedBy = "meeting_entity",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<UserEntity> userEntityList = new ArrayList<>();

    public MeetingEntity(String meetingName, List<UserEntity> userEntityList) {
        this.meetingName = meetingName;
        this.userEntityList = userEntityList;
    }

    public List<UserEntity> getUserEntityList() {
        return userEntityList;
    }

    public void addUserEntity(UserEntity userEntity) {
        if(!this.userEntityList.contains(userEntity)) {
            this.userEntityList.add(userEntity);
            userEntity.setMeetingEntity(this);
        }
    }

    public void removeUserEntity(UserEntity userEntity) {
        if(this.userEntityList.contains(userEntity)) {
            this.userEntityList.remove(userEntity);
            userEntity.setMeetingEntity(null);
        }
    }
}
