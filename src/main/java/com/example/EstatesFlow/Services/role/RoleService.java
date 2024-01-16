package com.example.EstatesFlow.Services.role;


import com.example.EstatesFlow.Entities.Role.Role;

public interface RoleService {

    public Role fetchRoleByName(final String roleName);
}
