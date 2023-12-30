package com.example.EstatesFlow.DAO.Repositories;

import com.example.EstatesFlow.DAO.Entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
