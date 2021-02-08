package de.share_your_idea.usermeetingsearch.util;

import de.share_your_idea.usermeetingsearch.entity.UserMeetingEntity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Converter
public class UserMeetingEntityStringListConverter implements AttributeConverter<List<UserMeetingEntity>, String> {

    @Override
    public String convertToDatabaseColumn(List<UserMeetingEntity> userMeetingEntityList) {
        String userMeetingNameString = "";
        if (userMeetingEntityList != null) {
            for (UserMeetingEntity userMeetingEntity : userMeetingEntityList) {
                userMeetingNameString += userMeetingEntity.getMeetingName() + ";";
            }
        }
        return userMeetingNameString;
    }

    @Override
    public List<UserMeetingEntity> convertToEntityAttribute(String string) {
        return Collections.emptyList();
    }
}
