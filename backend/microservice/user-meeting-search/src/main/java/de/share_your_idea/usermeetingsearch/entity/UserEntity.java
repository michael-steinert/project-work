package de.share_your_idea.usermeetingsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/*
Entity contains only the Properties specific to the Domain.
There is no Property Password and Relation between User and Meeting, because it is not needed in the Domain of User-Meeting-Search.
Target of the DDD is an Entity customized to their Domain.
*/

@Entity(name = "UserEntity")
@Table(name = "user_entity")
@Data
@NoArgsConstructor
@ToString
public class UserEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

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

    public UserEntity(String username, String userRole, String authorizationToken) {
        this.username = username;
        this.userRole = userRole;
        this.authorizationToken = authorizationToken;
    }
}