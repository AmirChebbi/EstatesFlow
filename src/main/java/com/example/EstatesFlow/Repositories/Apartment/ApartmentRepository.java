package com.example.EstatesFlow.Repositories.Apartment;

import com.example.EstatesFlow.Entities.Apartment.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
}
