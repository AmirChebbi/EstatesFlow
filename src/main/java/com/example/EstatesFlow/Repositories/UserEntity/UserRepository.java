package com.example.EstatesFlow.Repositories.UserEntity;

import com.example.EstatesFlow.Entities.UserEntity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Transactional
@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    @Query(value = "select u from UserEntity u where u.email =: email")
    Optional<UserEntity> findByEmail(@Param("email") String email);
}
