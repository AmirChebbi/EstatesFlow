package com.example.EstatesFlow.Repositories.Apartment;

import com.example.EstatesFlow.Entities.Apartment.Apartment;
import com.example.EstatesFlow.Entities.Project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    @Query(value = "select a from Project  p, Apartment a left join a where Project.id =:id and Apartment.sold =: sold")
    List<Apartment> getOneProjectApartmentsForSalePaged(Pageable pageable, long id, boolean sold);

    @Query(value = "select count(a) from Project  p, Apartment a left join a where Project.id =:id and Apartment.sold =: sold")
    long getOneProjectApartmentsPagedCount(Pageable pageable, long id, boolean sold);

    @Query(value = "SELECT p from Project p ")
    List<Apartment> getAllPaged(Pageable pageable);

    @Query(value = "select count (p) from Project p")
    long getPagedCount(Pageable pageable);

}
