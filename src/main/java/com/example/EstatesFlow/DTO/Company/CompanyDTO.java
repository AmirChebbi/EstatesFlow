package com.example.EstatesFlow.DTO.Company;

import com.example.EstatesFlow.Entities.Company.Company;
import com.example.EstatesFlow.Services.Project.ProjectService;
import java.util.List;

public record CompanyDTO (
        long id,
        String coName,
        String imageURL,
        String coDescription,
        List<String> projects
)
{}
