package com.example.EstatesFlow.DTO.UserEntity;

import com.example.EstatesFlow.Entities.Role.Role;

import java.util.Date;
import java.util.UUID;

public record UserDTO (
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        boolean isEnabled,
        Date creationDate,
        Role role
){
}
