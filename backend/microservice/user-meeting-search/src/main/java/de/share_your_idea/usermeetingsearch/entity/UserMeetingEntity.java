package de.share_your_idea.usermeetingsearch.entity;

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
@Table(
        name = "meeting_entity",
        uniqueConstraints = {
                @UniqueConstraint(name = "meeting_name_unique", columnNames = "meeting_name")
        }
)
@Data
@NoArgsConstructor
@ToString
@Embeddable
public class UserMeetingEntity {

    @Id
    @SequenceGenerator(
            name = "user_meeting_sequence",
            sequenceName = "user_meeting_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_meeting_sequence"
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
}
