package co.gov.mineducacion.repositories;

import co.gov.mineducacion.models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
