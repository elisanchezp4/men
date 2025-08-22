package co.gov.mineducacion.repositories;

import co.gov.mineducacion.models.entities.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
