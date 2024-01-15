package com.example.EstatesFlow.Services.Apartment;

import com.example.EstatesFlow.DTO.Apartment.ApartmentDTO;
import com.example.EstatesFlow.DTO.Apartment.ApartmentDTOMapper;
import com.example.EstatesFlow.Entities.Apartment.Apartment;
import com.example.EstatesFlow.Exceptions.ResourceNotFoundException;
import com.example.EstatesFlow.Exceptions.UnauthorizedActionException;
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
        if(apartments.isEmpty() && pageNumber > 1)
        {
            return getAllApartmentsForSale(id,1);
        }
        return ResponseHandler.generateResponse(apartments.stream().map(apartmentDTOMapper).toList(),HttpStatus.OK,apartments.size(), apartmentRepository.getOneProjectApartmentsPagedCount(pageable, id, false));
    }

    @Override
    public ResponseEntity<Object> getAll(long pageNumber) {
        final Pageable pageable = (Pageable) PageRequest.of((int) pageNumber, 5);
        List<Apartment> apartments = apartmentRepository.getAllPaged(pageable);
        if(apartments.isEmpty() && pageNumber > 1)
        {
            return getAll(1);
        }
        return ResponseHandler.generateResponse(apartments.stream().map(apartmentDTOMapper).toList(),HttpStatus.OK,apartments.size(), apartmentRepository.getPagedCount(pageable));
    }

    @Override
    public ResponseEntity<Object> addApartment(ApartmentDTO apartmentDTO) {
        if (apartmentRepository.findByDTO(apartmentDTO.apartmentNumber(),apartmentDTO.floorNumber(),apartmentDTO.apartmentDescription()).isEmpty()){
            apartmentRepository.save(
                    new Apartment(
                            apartmentDTO.apartmentNumber(),
                            apartmentDTO.floorNumber(),
                            apartmentDTO.apartmentDescription()
                    )
            );
            String successMessage = String.format("Apartment saved successfully !!");
            return ResponseHandler.generateResponse(successMessage, HttpStatus.OK);
        } else {
            throw new UnauthorizedActionException("This Apartment already exists !!");
        }
    }

    @Override
    public ResponseEntity<Object> updateApartment(long id,ApartmentDTO apartmentDTO) {
        Apartment apartment = apartmentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("This Apartment doesn't exist !!"));
        apartment.setSold(apartmentDTO.sold());
        apartment.setApartmentNumber(apartmentDTO.apartmentNumber());
        apartment.setApartmentDescription(apartmentDTO.apartmentDescription());
        apartment.setFloorNumber(apartmentDTO.floorNumber());
        apartmentRepository.save(apartment);
        String successMessage= String.format("Updates saved Successfully !!");
        return ResponseHandler.generateResponse(successMessage, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteById(long id) {
        if (apartmentRepository.existsById(id)) {
            apartmentRepository.deleteById(id);
            String successMessage = String.format("Apartment deleted successfully !!");
            return ResponseHandler.generateResponse(successMessage, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("This Apartment already don't exist");
        }
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
