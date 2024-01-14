package com.example.EstatesFlow.DTO.Project;

import com.example.EstatesFlow.Entities.Apartment.Apartment;
import java.util.List;

public record ProjectDTO (

    String projName,
    String projDescription,
    String address,
    String projImageURL,
    List<Apartment> apartments
){}
