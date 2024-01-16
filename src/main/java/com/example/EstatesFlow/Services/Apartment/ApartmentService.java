package com.example.EstatesFlow.Services.Apartment;

import com.example.EstatesFlow.DTO.Apartment.ApartmentDTO;
import com.example.EstatesFlow.Entities.Apartment.Apartment;
import com.example.EstatesFlow.Repositories.Apartment.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;


public interface ApartmentService {


    public ResponseEntity<Object> getById(long id);

    public ResponseEntity<Object> getAll(long pageNumber);

    public ResponseEntity<Object> getAllApartmentsForSale(long id ,long pageNumber);

    public ResponseEntity<Object> addApartment(ApartmentDTO apartmentDTO, long projectId);

    public ResponseEntity<Object> updateApartment( long id, ApartmentDTO apartmentDTO);

    public ResponseEntity<Object> deleteById(long id, long projectId);

}
