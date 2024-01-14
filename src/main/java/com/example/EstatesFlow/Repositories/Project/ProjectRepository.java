package com.example.EstatesFlow.Repositories.Project;

import com.example.EstatesFlow.Entities.Project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
