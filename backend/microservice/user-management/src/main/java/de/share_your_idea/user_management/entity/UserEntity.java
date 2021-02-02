package de.share_your_idea.user_management.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "UserEntity")
@Table(
        name = "user_table",
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
    private String user_role;

    @Column(
            name = "authorization_token",
            columnDefinition = "TEXT"
    )
    private String authorization_token;

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
