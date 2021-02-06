package de.share_your_idea.user_management.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

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
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
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
            nullable = true,
            columnDefinition = "TEXT"
    )
    private String authorization_token;

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
