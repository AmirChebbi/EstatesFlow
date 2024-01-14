package com.example.EstatesFlow.Controllers.Apartment;

import com.example.EstatesFlow.Entities.Apartment.Apartment;
import com.example.EstatesFlow.Services.Apartment.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> getById(@PathVariable Long id){
        return apartmentService.getById(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll(){
        return apartmentService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addApartment(@RequestBody Apartment apartment){
        return apartmentService.addApartment(apartment);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateApartment(@PathVariable long id, @RequestBody Apartment apartment){
        apartment.setId(id);
        return apartmentService.updateApartment(id ,apartment);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable long id){
        return apartmentService.deleteById(id);
    }

}

