package com.example.EstatesFlow.Repositories.Company;

import com.example.EstatesFlow.Entities.Company.Company;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Transactional
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query(value = "select c from Company c")
    List<Company> getAllPaged(Pageable pageable);

    @Query(value = "select count(c) from Company c")
    Long getCountPaged();

    @Query("SELECT c FROM Company c WHERE c.coName = :coName AND c.coDescription = :coDescription")
    Optional<Company> findByDTO(@Param("coName") String coName, @Param("coDescription") String coDescription);


}
