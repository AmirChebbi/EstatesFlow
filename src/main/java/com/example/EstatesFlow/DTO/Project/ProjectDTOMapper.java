package com.example.EstatesFlow.DTO.Project;

import com.example.EstatesFlow.DTO.Apartment.ApartmentDTOMapper;
import com.example.EstatesFlow.Entities.Project.Project;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class ProjectDTOMapper implements Function<Project, ProjectDTO> {
    private final ApartmentDTOMapper apartmentDTOMapper;

    public ProjectDTOMapper(ApartmentDTOMapper apartmentDTOMapper) {
        this.apartmentDTOMapper = apartmentDTOMapper;
    }

    @Override
    public ProjectDTO apply(Project project) {
        return new ProjectDTO(
                project.getProjName(),
                project.getProjDescription(),
                project.getAddress(),
                project.getProjImageURL(),
                project.getApartments().stream().map(apartmentDTOMapper).toList()
        );
    }
}
