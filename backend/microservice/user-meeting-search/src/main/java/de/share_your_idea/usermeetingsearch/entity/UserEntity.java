package de.share_your_idea.usermeetingsearch.entity;

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
@Data
@NoArgsConstructor
@ToString
@Embeddable
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
}