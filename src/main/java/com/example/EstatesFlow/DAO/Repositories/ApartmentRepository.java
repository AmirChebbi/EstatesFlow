package com.example.EstatesFlow.DAO.Repositories;

import com.example.EstatesFlow.DAO.Entities.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
}
