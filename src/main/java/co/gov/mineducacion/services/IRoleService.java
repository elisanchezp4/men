package co.gov.mineducacion.services;

import co.gov.mineducacion.models.entities.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRoleService {
    Role save(Role role);
    Page<Role> findAll(Pageable pageable);
    Role findById(Long roleId);
}
