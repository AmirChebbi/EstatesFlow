package com.example.EstatesFlow.DTO.Forum;


import com.example.EstatesFlow.DTO.Apartment.ApartmentDTO;
import com.example.EstatesFlow.DTO.Company.CompanyDTO;
import com.example.EstatesFlow.DTO.Project.ProjectDTO;

public record ForumDTO (
        long id,
        String fullName,
        String email,
        String phoneNumber,
        CompanyDTO relatedCompany,
        ProjectDTO relatedProject,
        ApartmentDTO relatedApartment,
        float price
){}
