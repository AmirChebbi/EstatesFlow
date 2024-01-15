package com.example.EstatesFlow.Controllers.Apartment;

import com.example.EstatesFlow.DTO.Apartment.ApartmentDTO;
import com.example.EstatesFlow.Services.Apartment.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/apartment")
public class ApartmentController {
    private final ApartmentService apartmentService;

    @Autowired
    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Object> getById(@PathVariable long id){
        return apartmentService.getById(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll(int pageNumber){
        return apartmentService.getAll(pageNumber);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addApartment(@RequestBody ApartmentDTO apartment){
        return apartmentService.addApartment(apartment);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateApartment(@PathVariable long id, @RequestBody ApartmentDTO apartment){
        return apartmentService.updateApartment(id ,apartment);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable long id){
        return apartmentService.deleteById(id);
    }

}

