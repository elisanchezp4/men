package co.gov.mineducacion.services.impl;

import co.gov.mineducacion.models.dtos.RoleDto;
import co.gov.mineducacion.models.dtos.UserRoleApplicationDto;
import co.gov.mineducacion.models.entities.Application;
import co.gov.mineducacion.models.entities.Role;
import co.gov.mineducacion.models.entities.User;
import co.gov.mineducacion.models.entities.UserRoleApplication;
import co.gov.mineducacion.models.pojo.UserRoleApplicationId;
import co.gov.mineducacion.repositories.RoleRepository;
import co.gov.mineducacion.repositories.UserRoleApplicationRepository;
import co.gov.mineducacion.services.IApplicationService;
import co.gov.mineducacion.services.IRoleService;
import co.gov.mineducacion.services.IUserRoleApplicationService;
import co.gov.mineducacion.services.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserRoleApplicationServiceImpl implements IUserRoleApplicationService {

    private final UserRoleApplicationRepository userRoleApplicationRepository;
    private final IUserService userService;
    private final IApplicationService applicationService;
    private final IRoleService roleService;
    private final RoleRepository roleRepository;

    @Override
    public List<UserRoleApplication> findAll() {
        return (List<UserRoleApplication>) userRoleApplicationRepository.findAll();
    }

    @Override
    public List<UserRoleApplication> assignRolesToUser(UserRoleApplicationDto userRoleApplicationDto) {

        User user = userService.findById(userRoleApplicationDto.getUserId());
        Application application = applicationService.findById(userRoleApplicationDto.getApplicationId());

        List<Role> roles = (List<Role>) roleRepository.findAllById(userRoleApplicationDto.getRoles());

        if (roles.isEmpty() || roles.size() != userRoleApplicationDto.getRoles().size()) {
            throw new RuntimeException("One or more roles not found");
        }

        List<UserRoleApplication> userRoleApplicationsToSave = new ArrayList<>();

        roles.forEach(role -> {
            UserRoleApplicationId userRoleApplicationId =
                    UserRoleApplicationId
                            .builder()
                            .userId(user.getId())
                            .roleId(role.getId())
                            .applicationId(application.getId())
                            .build();

            UserRoleApplication userRoleApplication =
                    UserRoleApplication
                            .builder()
                            .id(userRoleApplicationId)
                            .user(user)
                            .role(role)
                            .application(application)
                            .build();

            userRoleApplicationsToSave.add(userRoleApplication);
        });
        return (List<UserRoleApplication>) userRoleApplicationRepository.saveAll(userRoleApplicationsToSave);
    }

    @Override
    public int removeRoleToUser(RoleDto roleDto) {

        User user = userService.findById(roleDto.getUserId());
        List<Role> roles = new ArrayList<>();
        roleDto.getRoles().forEach(r -> {
            Role role = roleService.findById(r);
            roles.add(role);
        });
        return userRoleApplicationRepository.deleteByUserAndRoleIn(user, roles);
    }
}
