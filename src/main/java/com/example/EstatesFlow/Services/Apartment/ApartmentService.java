package com.example.EstatesFlow.Services.Apartment;

import com.example.EstatesFlow.DTO.Apartment.ApartmentDTO;
import org.springframework.http.ResponseEntity;


public interface ApartmentService {


    public ResponseEntity<Object> getById(long id);

    public ResponseEntity<Object> getAll(long pageNumber);

    public ResponseEntity<Object> getAllApartmentsForSale(long id ,long pageNumber);

    public ResponseEntity<Object> addApartment(ApartmentDTO apartmentDTO, long projectId);

    public ResponseEntity<Object> updateApartment(ApartmentDTO apartmentDTO);

    public ResponseEntity<Object> deleteById(long id, long projectId);

}
