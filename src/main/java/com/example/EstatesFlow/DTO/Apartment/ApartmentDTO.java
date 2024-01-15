package com.example.EstatesFlow.DTO.Apartment;

public record ApartmentDTO(
        long id,
        int apartmentNumber,

        int floorNumber,

        String apartmentDescription,

        boolean sold
) {
}
