package com.example.EstatesFlow.DTO.Apartment;

import com.example.EstatesFlow.Entities.Apartment.Apartment;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ApartmentDTOMapper implements Function<Apartment, ApartmentDTO> {

    @Override
    public ApartmentDTO apply(Apartment apartment) {
        return new ApartmentDTO(
                apartment.getId(),
                apartment.getApartmentNumber(),
                apartment.getFloorNumber(),
                apartment.getApartmentDescription(),
                apartment.isSold()
        );
    }
}
