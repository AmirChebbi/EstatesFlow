package com.example.EstatesFlow.Controllers.Apartment;

import com.example.EstatesFlow.DTO.Apartment.ApartmentDTO;
import com.example.EstatesFlow.Services.Apartment.ApartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/apartment")
public class ApartmentController {
    private final ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Object> getById(@PathVariable long id){
        return apartmentService.getById(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll(@RequestParam int pageNumber){
        return apartmentService.getAll(pageNumber);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addApartment(@RequestBody ApartmentDTO apartment, @RequestParam long projectId){
        return apartmentService.addApartment(apartment,projectId);
    }

    @GetMapping("/getAllForSale")
    public ResponseEntity<Object> getAllForSale(@PathVariable long projectId, @RequestParam long pageNumber){
        return apartmentService.getAllApartmentsForSale(projectId, pageNumber);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateApartment(@PathVariable long id, @RequestBody ApartmentDTO apartment){
        return apartmentService.updateApartment(id ,apartment);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteById(@RequestParam long id, @RequestParam long projectId){
        return apartmentService.deleteById(id,projectId);
    }

}

