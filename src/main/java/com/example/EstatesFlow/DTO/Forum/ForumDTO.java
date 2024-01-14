package com.example.EstatesFlow.DTO.Forum;


public record ForumDTO (
        String fullName,
        String email,
        String phoneNumber,
        String relatedCompany,
        String relatedProject,
        int wantedApartment,
        int wantedApartmentFloor,
        float price
){}
