package de.share_your_idea.user_registration.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user_table")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID user_id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String user_role;

    public String setUserRole(String user_role) {
        return this.user_role = user_role;
    }

    public String getUserRole() {
        return user_role;
    }
}
