package com.example.EstatesFlow.Controllers;

import com.example.EstatesFlow.DAO.Entities.Apartment;
import com.example.EstatesFlow.Services.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/apartment")
public class ApartmentController {
    private final ApartmentService apartmentService;

    @Autowired
    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @GetMapping("/getById/{id}")
    public Apartment getById(@PathVariable Long id){
        return apartmentService.getById(id);
    }

    @GetMapping("/getAll")
    public List<Apartment> getAll(){
        return apartmentService.getAll();
    }

    @PostMapping("/add")
    public Apartment addApartment(@RequestBody Apartment apartment){
        return apartmentService.addApartment(apartment);
    }

    @PutMapping("/update/{id}")
    public Apartment updateApartment(@PathVariable long id, @RequestBody Apartment apartment){
        apartment.setId(id);
        return apartmentService.updateApartment(id ,apartment);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable long id){
        apartmentService.deleteById(id);
    }

}

