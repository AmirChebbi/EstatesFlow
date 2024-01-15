package com.example.EstatesFlow.Repositories.Forum;

import com.example.EstatesFlow.Entities.Forum.Forum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface ForumRepository extends JpaRepository<Forum, Long> {
    @Query(value = "SELECT f from Forum f")
    List<Forum> findAllPaged(Pageable pageable);

    @Query(value = "SELECT count(f) from Forum f")
    long getCountPaged(Pageable pageable);
}
