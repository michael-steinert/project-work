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
    private final String firstName;
    @NotNull
    private final String lastName;
    @NotNull
    @Email
    private final String email;

    public UserEntity(@JsonProperty("userUid") UUID userUid,
                      @JsonProperty("username") String username,
                      @JsonProperty("firstName") String firstName,
                      @JsonProperty("lastName") String lastName,
                      @JsonProperty("email") String email) {
        this.userUid = userUid;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public static UserEntity newUser(UUID userUid, UserEntity userEntity) {
        return new UserEntity(userUid, userEntity.getUsername(), userEntity.getFirstName(), userEntity.getLastName(), userEntity.getEmail());
    }
}
