package de.share_your_idea.usermeetingsearch.entity;

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
    @SequenceGenerator(
            name = "search_query_sequence",
            sequenceName = "search_query_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "search_query_sequence"
    )
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

    @ElementCollection
    @CollectionTable(name = "user_entity_result")
    private List<UserEntity> userEntityResult;

    @ElementCollection
    @CollectionTable(name = "user_meeting_entity_result")
    private List<UserMeetingEntity> userMeetingEntityResult;
}