package com.example.EstatesFlow.Repositories.Company;

import com.example.EstatesFlow.Entities.Company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
