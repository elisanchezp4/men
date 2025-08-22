package co.gov.mineducacion.services;

import co.gov.mineducacion.models.dtos.PasswordDto;
import co.gov.mineducacion.models.dtos.UserDto;
import co.gov.mineducacion.models.entities.User;

import java.util.List;

public interface IUserService {

    User save(User user);
    User update(Long userId, UserDto user);
    List<User> findAll();
    User findById(Long userId);
    User findByEmail(String email);
    String changedPassword(PasswordDto passwordDto);
}
