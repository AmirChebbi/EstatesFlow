package com.example.EstatesFlow.DTO.Apartment;

import com.example.EstatesFlow.Entities.Apartment.Apartment;

import java.util.function.Function;

public class ApartmentDTOMapper implements Function<Apartment, ApartmentDTO> {

    @Override
    public ApartmentDTO apply(Apartment apartment) {
        return new ApartmentDTO(
                apartment.getApartmentNumber(),
                apartment.getFloorNumber(),
                apartment.getApartmentDescription(),
                apartment.isSold()
        );
    }
}
