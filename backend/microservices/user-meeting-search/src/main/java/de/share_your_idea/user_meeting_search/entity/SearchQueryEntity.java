package de.share_your_idea.user_meeting_search.entity;

import de.share_your_idea.user_meeting_search.util.UserEntityStringListConverter;
import de.share_your_idea.user_meeting_search.util.UserMeetingEntityStringListConverter;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "search_query_id",
            updatable = false
    )
    private Long searchQueryId;

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