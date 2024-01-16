package com.example.EstatesFlow.Repositories.Role;

import com.example.EstatesFlow.Entities.Role.Role;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Transactional
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(value = "select r from Role r where r.name =:name")
    Optional<Role> fetchRoleByName(@Param("name") String roleName);
}
