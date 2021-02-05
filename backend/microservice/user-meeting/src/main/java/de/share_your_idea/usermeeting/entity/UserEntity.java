package de.share_your_idea.usermeeting.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/*
Entity contains only the Properties specific to the Domain.
There is no password property, because it is not needed in the Domain of User-Meeting.
Target of the DDD is an Entity customized to their Domain.
*/

@Entity(name = "UserEntity")
@Table(
        name = "user_entity",
        uniqueConstraints = {
                @UniqueConstraint(name = "username_unique", columnNames = "username")
        }
)
@Data
@NoArgsConstructor
@ToString
public class UserEntity {

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
            name = "user_id",
            updatable = false
    )
    private Long userId;

    @Column(
            name = "username",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String username;

    @Column(
            name = "user_role",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String userRole;

    @Column(
            name = "authorization_token",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String authorizationToken;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "meeting_id",
            referencedColumnName = "meeting_id",
            nullable = true,
            updatable = true
    )
    private MeetingEntity meetingEntity;
}
