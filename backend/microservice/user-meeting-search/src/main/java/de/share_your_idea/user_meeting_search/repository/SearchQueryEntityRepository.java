package de.share_your_idea.user_meeting_search.repository;

import de.share_your_idea.user_meeting_search.entity.SearchQueryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchQueryEntityRepository extends JpaRepository<SearchQueryEntity, Long> {
}
