package de.share_your_idea.user_meeting.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/*
Entity contains only the Properties specific to the Domain.
There is no Constraint for unique Username, because it is not needed in the Domain of User-Meeting.
The Target of the DDD is an Entity customized to their Domain.
*/

@Entity(name = "UserEntity")
@Table(name = "user_entity")
@Data
@NoArgsConstructor
@ToString
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
            name = "password",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String password;

    @Column(
            name = "user_role",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

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
    private UserMeetingEntity userMeetingEntity;
}
