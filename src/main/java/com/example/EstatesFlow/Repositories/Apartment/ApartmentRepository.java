package com.example.EstatesFlow.Repositories.Apartment;

import com.example.EstatesFlow.Entities.Apartment.Apartment;
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
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    @Query("SELECT a FROM Project p LEFT JOIN p.apartments a WHERE p.id = :id AND a.sold = :sold")
    List<Apartment> getOneProjectApartmentsForSalePaged(@Param("id") long projectId, @Param("sold") boolean sold, Pageable pageable);

    @Query("SELECT count (a) FROM Project p LEFT JOIN p.apartments a WHERE p.id = :id AND a.sold = :sold")
    Long getOneProjectApartmentsPagedCount(@Param("id")long id, @Param("sold") boolean sold);

    @Query(value = "SELECT a from Apartment a ")
    List<Apartment> getAllPaged( Pageable pageable);

    @Query(value = "select count (a) from Apartment a")
    Long getPagedCount();

    @Query("SELECT a FROM Apartment a WHERE a.apartmentDescription = :description AND a.apartmentNumber = :apartNum AND a.floorNumber = :floorNum")
    Optional<Apartment> findByDTO(
            @Param("description") String description,
            @Param("apartNum") int apartNum,
            @Param("floorNum") int floorNum
    );
}
