package de.share_your_idea.usermeetingsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/*
Entity contains only the Properties specific to the Domain.
There is no Relation between User and Meeting, because it is not needed in the Domain of User-Meeting-Search.
Target of the DDD is an Entity customized to their Domain.
*/

@Entity(name = "MeetingEntity")
@Table(name = "meeting_entity")
@Data
@NoArgsConstructor
@ToString
public class UserMeetingEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "meeting_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String meetingName;

    public UserMeetingEntity(String meetingName) {
        this.meetingName = meetingName;
    }
}
