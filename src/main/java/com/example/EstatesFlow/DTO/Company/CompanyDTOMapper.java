package com.example.EstatesFlow.DTO.Company;

import com.example.EstatesFlow.Entities.Company.Company;
import com.example.EstatesFlow.Services.Project.ProjectServiceImpl;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

import java.util.function.Function;

public class CompanyDTOMapper implements Function<Company, CompanyDTO> {

    @Override
    public CompanyDTO apply(Company company) {
        return new CompanyDTO(
                company.getId(),
                company.getCoName(),
                company.getImageURL(),
                company.getCoDescription(),
                ProjectServiceImpl.mapProjectsToNames(company.getProjects())
        );
    }
}
