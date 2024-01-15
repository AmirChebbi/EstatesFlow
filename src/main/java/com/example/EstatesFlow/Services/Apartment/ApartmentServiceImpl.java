package com.example.EstatesFlow.Services.Apartment;

import com.example.EstatesFlow.DTO.Apartment.ApartmentDTO;
import com.example.EstatesFlow.DTO.Apartment.ApartmentDTOMapper;
import com.example.EstatesFlow.Entities.Apartment.Apartment;
import com.example.EstatesFlow.Entities.Project.Project;
import com.example.EstatesFlow.Exceptions.ResourceNotFoundException;
import com.example.EstatesFlow.Repositories.Apartment.ApartmentRepository;
import com.example.EstatesFlow.Utility.ResponseHandler;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Objects;

public class ApartmentServiceImpl implements ApartmentService{

    private final ApartmentRepository apartmentRepository;

    private final ApartmentDTOMapper apartmentDTOMapper;

    public ApartmentServiceImpl(ApartmentRepository apartmentRepository, ApartmentDTOMapper apartmentDTOMapper) {
        this.apartmentRepository = apartmentRepository;
        this.apartmentDTOMapper = apartmentDTOMapper;
    }

    @Override
    public ResponseEntity<Object> getById(long id) {
        Apartment apartment = apartmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This apartment Doesn't exist in our system !!"));
        return ResponseHandler.generateResponse(apartment, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getAllApartmentsForSale(long id, long pageNumber) {
        final Pageable pageable = (Pageable) PageRequest.of((int) pageNumber, 5);
        List<Apartment> apartments = apartmentRepository.getOneProjectApartmentsForSalePaged(pageable, id, false);
        return ResponseHandler.generateResponse(apartments.stream().map(apartmentDTOMapper).toList(),HttpStatus.OK,apartments.size(), apartmentRepository.getOneProjectApartmentsPagedCount(pageable, id, false));
    }

    @Override
    public ResponseEntity<Object> getAll(long pageNumber) {
        final Pageable pageable = (Pageable) PageRequest.of((int) pageNumber, 5);
        List<Apartment> apartments = apartmentRepository.getAllPaged(pageable);
        return ResponseHandler.generateResponse(apartments.stream().map(apartmentDTOMapper).toList(),HttpStatus.OK,apartments.size(), apartmentRepository.getPagedCount(pageable));
    }

    @Override
    public ResponseEntity<Object> addApartment(ApartmentDTO apartmentDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Object> updateApartment(long id,ApartmentDTO apartmentDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Object> deleteById(long id) {
        return null;
    }


    public static List<Apartment> filterSoldApartments(List<Apartment> apartments){
        for (Apartment apartment : apartments){
            if (Objects.equals( apartment.isSold(),true)){
                apartments.remove(apartment);
            }
        }
        return apartments;
    }
}
