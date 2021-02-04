package de.share_your_idea.usermeeting.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "user_id",
            updatable = false
    )
    private UUID user_id;

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
    private String user_role;

    @Column(
            name = "authorization_token",
            columnDefinition = "TEXT"
    )
    private String authorization_token;

    @ManyToOne
    @JoinColumn(
            name = "meeting_id",
            nullable = false,
            referencedColumnName = "meeting_id",
            foreignKey = @ForeignKey(
                    name = "meeting_entity_user_entity_meeting_entity_id_fk"
            )
    )
    private MeetingEntity meetingEntity;

    public UserEntity(String username, String user_role) {
        this.username = username;
        this.user_role = user_role;
    }
}
