package de.share_your_idea.user_management.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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
    private Long userId;

    @Column(
            name = "username",
            nullable = false,
            columnDefinition = "TEXT"
    )
    //@NotBlank(message = "Username must be not empty")
    private String username;

    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "TEXT"
    )
    //@NotBlank(message = "Password must be not empty")
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
            nullable = true,
            columnDefinition = "TEXT"
    )
    //@NotBlank(message = "AuthorizationToken must be not empty")
    private String authorizationToken;

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
