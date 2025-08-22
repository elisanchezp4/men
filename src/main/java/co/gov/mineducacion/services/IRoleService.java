package co.gov.mineducacion.services;

import co.gov.mineducacion.models.entities.Role;

import java.util.List;

public interface IRoleService {
    Role save(Role role);
    List<Role> findAll();
    Role findById(Long roleId);
}
