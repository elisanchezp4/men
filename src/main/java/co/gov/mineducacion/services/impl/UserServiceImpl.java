package co.gov.mineducacion.services.impl;

import co.gov.mineducacion.exceptions.business.ResourceNotFoundException;
import co.gov.mineducacion.models.dtos.PasswordDto;
import co.gov.mineducacion.models.dtos.UserDto;
import co.gov.mineducacion.models.entities.User;
import co.gov.mineducacion.repositories.UserRepository;
import co.gov.mineducacion.services.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public User save(User user) {

        String encodedPassword = passwordEncoder.encode(user.getPasswordHash());

        user.setIsMigrated(false);
        user.setPasswordHash(encodedPassword);
        return userRepository.save(user);
    }

    @Override
    public User update(Long userId, UserDto user) {

        User userFound = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", userId));

        userFound.setFirstName(user.getFirstName());
        userFound.setSecondName(user.getSecondName());
        userFound.setLastName(user.getLastName());
        userFound.setSecondLastName(user.getSecondLastName());
        userFound.setEmail(user.getEmail());
        userFound.setUpdatedUser(user.getUpdatedUser());

        return userRepository.save(userFound);
    }
    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", userId));
    }
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("user", email));
    }

    @Override
    public String changedPassword(PasswordDto passwordDto) {

        User userFound = findByEmail(passwordDto.getEmail());
        String encodedPassword = passwordEncoder.encode(passwordDto.getPassword());
        userFound.setPasswordHash(encodedPassword);
        userRepository.save(userFound);

        return "su contraseña se ha cambiado exitosamente";
    }
}
