package co.gov.mineducacion.services.impl;

import co.gov.mineducacion.exceptions.business.ResourceNotFoundException;
import co.gov.mineducacion.models.dtos.ApplicationDto;
import co.gov.mineducacion.models.entities.Application;
import co.gov.mineducacion.repositories.ApplicationRepository;
import co.gov.mineducacion.services.IApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements IApplicationService {

    private final ApplicationRepository applicationRepository;

    @Override
    public Application save(Application application) {
        return applicationRepository.save(application);
    }

    @Override
    public Application update(Long applicationId, ApplicationDto application) {

        Application applicationFound = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("application", applicationId));

        applicationFound.setCodeType(application.getApplicationTypeCode());
        applicationFound.setApplicationName(application.getApplicationName());
        applicationFound.setDescription(application.getDescription());
        applicationFound.setUpdatedUser(application.getUpdatedUser());

        return applicationRepository.save(applicationFound);
    }

    @Override
    public List<Application> findAll() {
        return (List<Application>) applicationRepository.findAll();
    }

    @Override
    public Application findById(Long applicationId) {
        return applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("application", applicationId));
    }
    @Override
    public Application findByCodeType(String codeType){
        return applicationRepository.findByCodeType(codeType)
                .orElseThrow(() -> new ResourceNotFoundException("application", codeType));
    }
}


