package de.share_your_idea.usermeetingsearch.repository;

import de.share_your_idea.usermeetingsearch.entity.SearchQueryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchQueryEntityRepository extends JpaRepository<SearchQueryEntity, Long> {
}
