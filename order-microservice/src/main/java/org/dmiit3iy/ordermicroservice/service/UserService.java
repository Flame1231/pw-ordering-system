package org.dmiit3iy.ordermicroservice.service;

import lombok.RequiredArgsConstructor;
import org.dmiit3iy.ordermicroservice.model.User;
import org.dmiit3iy.ordermicroservice.model.dto.RegistrationRequest;
import org.dmiit3iy.ordermicroservice.mapper.UserMapper;
import org.dmiit3iy.ordermicroservice.model.dto.UserDTO;
import org.dmiit3iy.ordermicroservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    /**
     * Метод по добавлению пользователя
     *
     * @param user
     * @return
     */
    public User add(User user) {
        if (existByUsername(user.getUsername()) && existByEmail(user.getEmail())) {
            throw new IllegalArgumentException("username and email are both unique");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Метод поиска пользователя по ID
     *
     * @param id
     * @return
     */
    public User findByID(long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("There is no user with this Id"));
    }

    /**
     * Метод поиска пользователя по ID
     *
     * @param id
     * @return UserDTO
     */
    public UserDTO findByIDReturnDro(long id) {
        return userMapper.toUserDTO(findByID(id));
    }

    /**
     * Метод поиска по username
     *
     * @param username
     * @return
     */
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Метод поиска по email
     *
     * @param email
     * @return
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Метод удаления пользователя по ID
     *
     * @param id
     */
    public void delete(Long id) {
        User user = findByID(id);
        userRepository.delete(user);

    }

    /**
     * Метод обновления пользователя
     *
     * @param user
     * @return
     */
    public User update(User user) {
        User baseUser = findByID(user.getId());
        baseUser=userMapper.updateUser(user);
        return userRepository.save(baseUser);


    }

    /**
     * Метод для проверки существования имиени пользователя в БД
     *
     * @param username
     * @return
     */
    private boolean existByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    /**
     * Метод для проверки существования почты в БД
     *
     * @param email
     * @return
     */
    private boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


    /**
     * Метод для реализации логики регистрации нового пользователя
     *
     * @param request
     * @return
     */
    public String registerUser(RegistrationRequest request) {
        if (findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already taken");
        }
        if (findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }
        User newUser = userMapper.toUser(request);
        add(newUser);
        return "User registered successfully";
    }


}
