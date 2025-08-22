package co.gov.mineducacion.repositories;

import co.gov.mineducacion.models.entities.Role;
import co.gov.mineducacion.models.entities.User;
import co.gov.mineducacion.models.entities.UserRoleApplication;
import co.gov.mineducacion.models.pojo.UserRoleApplicationId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRoleApplicationRepository extends CrudRepository<UserRoleApplication, UserRoleApplicationId> {

    @Modifying
    @Transactional
    int deleteByUserAndRoleIn(User user, List<Role> roles);
}
