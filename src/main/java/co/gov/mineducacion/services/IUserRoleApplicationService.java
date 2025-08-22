package co.gov.mineducacion.services;

import co.gov.mineducacion.models.dtos.RoleDto;
import co.gov.mineducacion.models.dtos.UserRoleApplicationDto;
import co.gov.mineducacion.models.entities.UserRoleApplication;

import java.util.List;

public interface IUserRoleApplicationService {

    List<UserRoleApplication> findAll();
    int removeRoleToUser(RoleDto roleDto);
    List<UserRoleApplication> assignRolesToUser(UserRoleApplicationDto userRoleApplicationDto);
}
