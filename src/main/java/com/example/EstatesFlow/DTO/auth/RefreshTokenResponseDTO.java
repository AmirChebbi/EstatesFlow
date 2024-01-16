package com.example.EstatesFlow.DTO.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefreshTokenResponseDTO {
    private String accessToken;
    private String refreshToken;
}