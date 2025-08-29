package co.gov.mineducacion.services;

import co.gov.mineducacion.models.dtos.PasswordDto;
import co.gov.mineducacion.models.dtos.UserDto;
import co.gov.mineducacion.models.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {

    User save(User user);
    User update(Long userId, UserDto user);
    Page<User> findAll(Pageable pageable);
    User findById(Long userId);
    User findByEmail(String email);
    String changedPassword(PasswordDto passwordDto);
}
