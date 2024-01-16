package com.example.EstatesFlow.Repositories.UserEntity;

import com.example.EstatesFlow.Entities.UserEntity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    @Query(value = "select u from UserEntity u where UserEntity.email =: email")
    Optional<UserEntity> findByEmail(String email);
}
