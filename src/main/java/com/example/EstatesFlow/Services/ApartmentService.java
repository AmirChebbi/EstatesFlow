package com.example.EstatesFlow.Services;

import com.example.EstatesFlow.DAO.Entities.Apartment;
import com.example.EstatesFlow.DAO.Repositories.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;

@Service
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;

    @Autowired
    public ApartmentService(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    public Apartment getById(@PathVariable Long id){
        return apartmentRepository.getById(id);
    }

    public List<Apartment> getAll(){
        return apartmentRepository.findAll();
    }

    public Apartment addApartment(@RequestBody Apartment apartment){
        return apartmentRepository.save(apartment);
    }

    public Apartment updateApartment(@PathVariable long id, @RequestBody Apartment apartment){
        apartment.setId(id);
        return apartmentRepository.save(apartment);
    }

    public void deleteById(@PathVariable long id) {
        apartmentRepository.deleteById(id);
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
