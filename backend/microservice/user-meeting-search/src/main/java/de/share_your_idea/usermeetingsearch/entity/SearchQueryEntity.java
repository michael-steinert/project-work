package de.share_your_idea.usermeetingsearch.entity;

import de.share_your_idea.usermeetingsearch.util.UserEntityStringListConverter;
import de.share_your_idea.usermeetingsearch.util.UserMeetingEntityStringListConverter;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity(name = "SearchQueryEntity")
@Table(name = "search_query_entity")
@Data
@NoArgsConstructor
@ToString
public class SearchQueryEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "search_query",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String searchQuery;

    @Column(
            name = "user_entity_result",
            nullable = true,
            columnDefinition = "TEXT"
    )
    @Convert(converter = UserEntityStringListConverter.class)
    private List<UserEntity> userEntityResult;

    @Column(
            name = "user_meeting_entity_result",
            nullable = true,
            columnDefinition = "TEXT"
    )
    @Convert(converter = UserMeetingEntityStringListConverter.class)
    private List<UserMeetingEntity> userMeetingEntityResult;
}