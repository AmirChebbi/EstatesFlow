package com.example.EstatesFlow.DTO.Apartment;

public record ApartmentDTO(
        int apartmentNumber,

        int floorNumber,

        String apartmentDescription,

        boolean sold
) {
}
