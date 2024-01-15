package com.example.EstatesFlow.Repositories.Apartment;

import com.example.EstatesFlow.DTO.Apartment.ApartmentDTO;
import com.example.EstatesFlow.Entities.Apartment.Apartment;
import com.example.EstatesFlow.Entities.Project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    @Query(value = "select a from Project  p, Apartment a left join a where Project.id =:id and Apartment.sold =: sold")
    List<Apartment> getOneProjectApartmentsForSalePaged(Pageable pageable, long id, boolean sold);

    @Query(value = "select count(a) from Project  p, Apartment a left join a where Project.id =:id and Apartment.sold =: sold")
    long getOneProjectApartmentsPagedCount(Pageable pageable, long id, boolean sold);

    @Query(value = "SELECT a from Apartment a ")
    List<Apartment> getAllPaged(Pageable pageable);

    @Query(value = "select count (a) from Apartment a")
    long getPagedCount(Pageable pageable);

    @Query(value = "select a from Apartment a where Apartment.apartmentDescription =: description and Apartment.apartmentNumber =: apartNum and Apartment.floorNumber =: floorNum")
    Optional<Apartment> findByDTO(int apartNum, int floorNum, String description);
}
