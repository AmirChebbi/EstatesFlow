package com.example.EstatesFlow.DTO.Company;

import com.example.EstatesFlow.DTO.Project.ProjectDTOMapper;
import com.example.EstatesFlow.Entities.Company.Company;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CompanyDTOMapper implements Function<Company, CompanyDTO> {

    private final ProjectDTOMapper projectDTOMapper;

    public CompanyDTOMapper(ProjectDTOMapper projectDTOMapper) {
        this.projectDTOMapper = projectDTOMapper;
    }

    @Override
    public CompanyDTO apply(Company company) {
        return new CompanyDTO(
                company.getId(),
                company.getCoName(),
                company.getImageURL(),
                company.getCoDescription(),
                company.getProjects().stream().map(projectDTOMapper).toList()
        );
    }
}
