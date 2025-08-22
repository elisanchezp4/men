package co.gov.mineducacion.services;

import co.gov.mineducacion.models.dtos.ApplicationDto;
import co.gov.mineducacion.models.entities.Application;

import java.util.List;

public interface IApplicationService {

    Application save(Application application);
    Application update(Long applicationId, ApplicationDto application);
    List<Application> findAll();
    Application findById(Long applicationId);
    Application findByCodeType(String codeType);
}
