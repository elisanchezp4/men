package co.gov.mineducacion.services.impl;

import co.gov.mineducacion.exceptions.business.ResourceNotFoundException;
import co.gov.mineducacion.models.entities.Role;
import co.gov.mineducacion.repositories.RoleRepository;
import co.gov.mineducacion.services.IRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> findAll() {
        return (List<Role>) roleRepository.findAll();
    }

    @Override
    public Role findById(Long roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("role", roleId));
    }
}
