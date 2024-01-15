package com.example.EstatesFlow.Repositories.Company;

import com.example.EstatesFlow.Entities.Company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query(value = "select c from Company c")
    List<Company> getAllPaged(Pageable pageable);

    @Query(value = "select coutn(c) from Company c")
    long getCountPaged(Pageable pageable);

    @Query(value = "SELECT c from Company c where Company.coName =: coName and Company.coDescription =: coDescription")
    Optional<Company> findByDTO(String coName, String coDescription);
}
