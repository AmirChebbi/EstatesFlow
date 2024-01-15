package com.example.EstatesFlow.DTO.Project;

import com.example.EstatesFlow.DTO.Apartment.ApartmentDTO;
import java.util.List;

public record ProjectDTO (
    String projName,
    String projDescription,
    String address,
    String projImageURL,
    List<ApartmentDTO> apartments
){}
