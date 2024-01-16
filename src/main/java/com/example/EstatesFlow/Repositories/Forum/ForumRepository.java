package com.example.EstatesFlow.Repositories.Forum;

import com.example.EstatesFlow.Entities.Forum.Forum;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Transactional
@Repository
public interface ForumRepository extends JpaRepository<Forum, Long> {
    @Query(value = "SELECT f from Forum f")
    List<Forum> findAllPaged(Pageable pageable);

    @Query(value = "SELECT count(f) from Forum f")
    Long getCountPaged();
}
