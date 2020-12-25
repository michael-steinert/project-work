package de.share_your_idea.user_management.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserEntity {
    private UUID userUid;
    @NotNull
    private final String username;
    @NotNull
    private final String password;
    @NotNull
    private final String user_role;
    private final String firstName;
    private final String lastName;
    @Email
    private final String email;

    public UserEntity(@JsonProperty("user_uid") UUID userUid,
                      @JsonProperty("username") String username,
                      @JsonProperty("password") String password,
                      @JsonProperty("user_role") String user_role,
                      @JsonProperty("first_name") String firstName,
                      @JsonProperty("last_name") String lastName,
                      @JsonProperty("email") String email) {
        this.userUid = userUid;
        this.username = username;
        this.password = password;
        this.user_role = user_role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public static UserEntity newUser(UUID userUid, UserEntity userEntity) {
        return new UserEntity(userUid, userEntity.getUsername(), userEntity.getPassword(), userEntity.getUser_role(), userEntity.getFirstName(), userEntity.getLastName(), userEntity.getEmail());
    }
}
