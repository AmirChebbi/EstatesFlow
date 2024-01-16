package com.example.EstatesFlow.Repositories.Project;

import com.example.EstatesFlow.Entities.Project.Project;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Transactional
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query(value = "SELECT p from Project p ")
    List<Project> getAllPaged(Pageable pageable);

    @Query(value = "select count (p) from Project p")
    Long getPagedCount();

    @Query(value = "select p from Project p where p.projName =: name and p.address =: adress and p.projDescription =: description")
    Optional<Project> findByDTO(@Param("name") String name,  @Param("address") String address, @Param("description") String description);
}
