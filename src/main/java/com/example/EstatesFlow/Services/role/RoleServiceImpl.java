package com.example.EstatesFlow.Services.role;


import com.example.EstatesFlow.Entities.Role.Role;
import com.example.EstatesFlow.Exceptions.ResourceNotFoundException;
import com.example.EstatesFlow.Repositories.Role.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role fetchRoleByName(String roleName) {
        return roleRepository.fetchRoleByName(roleName).orElseThrow(
                ()-> new ResourceNotFoundException("Thir role doesn't exist !!")
        );
    }
}
