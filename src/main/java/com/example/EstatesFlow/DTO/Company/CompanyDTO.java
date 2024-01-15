package com.example.EstatesFlow.DTO.Company;

import com.example.EstatesFlow.DTO.Project.ProjectDTO;
import com.example.EstatesFlow.Entities.Company.Company;
import com.example.EstatesFlow.Services.Project.ProjectService;
import java.util.List;

public record CompanyDTO (
        String coName,
        String imageURL,
        String coDescription,
        List<ProjectDTO> projects
)
{}
