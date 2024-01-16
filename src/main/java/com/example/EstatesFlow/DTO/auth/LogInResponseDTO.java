package com.example.EstatesFlow.DTO.auth;

import com.example.EstatesFlow.DTO.UserEntity.UserDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogInResponseDTO {
    private UserDTO userDTO;
    private String accessToken;
    private String refreshToken;
}
