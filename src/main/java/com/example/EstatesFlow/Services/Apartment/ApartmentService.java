package com.example.EstatesFlow.Services.Apartment;

import com.example.EstatesFlow.Entities.Apartment.Apartment;
import com.example.EstatesFlow.Repositories.Apartment.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;

@Service
public interface ApartmentService {


    public ResponseEntity<Object> getById(@PathVariable Long id);

    public ResponseEntity<Object> getAll();

    public ResponseEntity<Object> addApartment(@RequestBody Apartment apartment);

    public ResponseEntity<Object> updateApartment(@PathVariable long id, @RequestBody Apartment apartment);

    public ResponseEntity<Object> deleteById(@PathVariable long id);

/*
*/

}
