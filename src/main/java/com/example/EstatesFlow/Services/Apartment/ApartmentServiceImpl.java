package com.example.EstatesFlow.Services.Apartment;

import com.example.EstatesFlow.Entities.Apartment.Apartment;
import com.example.EstatesFlow.Exceptions.ResourceNotFoundException;
import com.example.EstatesFlow.Repositories.Apartment.ApartmentRepository;
import com.example.EstatesFlow.Utility.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

public class ApartmentServiceImpl implements ApartmentService{

    private final ApartmentRepository apartmentRepository;

    public ApartmentServiceImpl(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    @Override
    public ResponseEntity<Object> getById(Long id) {
        Apartment apartment = apartmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This apartment Doesn't exist in our system !!"));
        return ResponseHandler.generateResponse(apartment, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<Object> addApartment(Apartment apartment) {
        return null;
    }

    @Override
    public ResponseEntity<Object> updateApartment(long id, Apartment apartment) {
        return null;
    }

    @Override
    public ResponseEntity<Object> deleteById(long id) {
        return null;
    }


    public static List<Apartment> filterSoldApartments(List<Apartment> apartments){
        for (Apartment apartment : apartments){
            if (Objects.equals( apartment.getState(),"Sold")){
                apartments.remove(apartment);
            }
        }
        return apartments;
    }
}
