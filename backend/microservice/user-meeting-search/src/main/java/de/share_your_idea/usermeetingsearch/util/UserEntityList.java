package de.share_your_idea.usermeetingsearch.util;

import de.share_your_idea.usermeetingsearch.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class UserEntityList {
    private List<UserEntity> userEntityList;

    public UserEntityList() {
        userEntityList = new ArrayList<>();
    }
}
