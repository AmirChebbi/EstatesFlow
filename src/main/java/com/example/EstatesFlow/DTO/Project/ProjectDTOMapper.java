package com.example.EstatesFlow.DTO.Project;

import com.example.EstatesFlow.Entities.Project.Project;

import java.util.function.Function;

public class ProjectDTOMapper implements Function<Project, ProjectDTO> {
    @Override
    public ProjectDTO apply(Project project) {
        return new ProjectDTO(
                project.getId(),
                project.getProjName(),
                project.getProjDescription(),
                project.getAddress(),
                project.getProjImageURL(),
                project.getApartments()
        );
    }
}
