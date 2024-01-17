package com.example.EstatesFlow.Repositories.Token;


import com.example.EstatesFlow.Entities.token.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {


    @Query(value = "SELECT RT FROM RefreshToken RT WHERE RT.refreshToken = :refreshToken")
    Optional<RefreshToken> fetchByToken(@Param("refreshToken")final String refreshToken);

    @Query(value = "SELECT RT FROM RefreshToken RT WHERE RT.userEntity.id = :userId")
    List<RefreshToken> fetchAllRefreshTokenByUserId(@Param("userId")final UUID userId);

}
