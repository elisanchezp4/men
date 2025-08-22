package co.gov.mineducacion.repositories;

import co.gov.mineducacion.models.entities.Application;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ApplicationRepository extends CrudRepository<Application, Long> {
    Optional<Application> findByCodeType(String codeType);
}
