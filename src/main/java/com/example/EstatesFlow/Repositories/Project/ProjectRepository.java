package com.example.EstatesFlow.Repositories.Project;

import com.example.EstatesFlow.Entities.Project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query(value = "SELECT p from Project p ")
    List<Project> getAllPaged(Pageable pageable);

    @Query(value = "select count (p) from Project p")
    long getPagedCount(Pageable pageable);

    @Query(value = "select p from Project p where Project.projName =: name and Project.address =: adress and Project.projDescription =: description")
    Optional<Project> findByDTO(String name, String address, String description);
}
