package com.rubic.cube.service;

import com.rubic.cube.controller.model.request.UpdatePasswordRequest;
import com.rubic.cube.controller.model.request.UpdateUserRequest;
import com.rubic.cube.entity.User;
import com.rubic.cube.exception.BusinessCodeException;
import com.rubic.cube.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

import static com.rubic.cube.exception.ExceptionMessage.*;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long create(User user) throws BusinessCodeException {
        Optional<User> savedUser = userRepository.findByUsername(user.getUsername());
        if (savedUser.isPresent()) {
            throw new BusinessCodeException(USERNAME_IS_ALREADY_EXISTS, USERNAME_IS_ALREADY_EXISTS_MSG);
        }
        return userRepository.save(user).getId();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessCodeException(USER_NOT_FOUND, USER_NOT_FOUND_MSG));
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BusinessCodeException(USER_NOT_FOUND, USER_NOT_FOUND_MSG));
    }

    @Transactional
    public void deleteByUsername(String username) throws BusinessCodeException {
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new BusinessCodeException(USER_NOT_FOUND, USER_NOT_FOUND_MSG);
        }
        userRepository.deleteByUsername(username);
    }

    @Transactional
    public void deleteById(Long id) throws BusinessCodeException {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new BusinessCodeException(USER_NOT_FOUND, USER_NOT_FOUND_MSG);
        }
        userRepository.deleteById(id);
    }

    @Transactional
    public Long updateByUsername(String username, UpdateUserRequest updateUserRequest) throws BusinessCodeException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessCodeException(USER_NOT_FOUND, USER_NOT_FOUND_MSG));
        if (StringUtils.hasText(updateUserRequest.getFirstName())) {
            user.setFirstName(updateUserRequest.getFirstName());
        }
        if (StringUtils.hasText(updateUserRequest.getLastName())) {
            user.setLastName(updateUserRequest.getLastName());
        }

        userRepository.save(user);
        return user.getId();
    }

    public List<User> findAll(int page, int limit) {
        Pageable pageable = Pageable.ofSize(limit).withPage(page);
        Page<User> users = userRepository.findAll(pageable);
        return users.toList();
    }

    public void updatePassword(String username, UpdatePasswordRequest updatePasswordRequest) throws BusinessCodeException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessCodeException(USER_NOT_FOUND, USER_NOT_FOUND_MSG));
        if (user.getPassword().equals(updatePasswordRequest.getCurrentPassword())) {
            user.setPassword(updatePasswordRequest.getNewPassword());
            userRepository.save(user);
        } else {
            throw new BusinessCodeException(INCORRECT_PASSWORD, INCORRECT_PASSWORD_MSG);
        }

        String s = "{ \"name\": \"product-connector\", \"config\": { \"connector.class\": \"io.debezium.connector.postgresql.PostgresConnector\", \"database.hostname\": \"postgres-db-1\", \"database.port\": \"5432\", \"database.user\": \"postgres\", \"database.password\": \"123\", \"database.dbname\": \"product_db\", \"database.server.name\": \"PostgreSQL15\", \"table.include.list\": \"public.product\", \"plugin.name\": \"pgoutput\", \"heartbeat.topics.prefix\": \"cube\", \"transforms\": \"unwrap\", \"transforms.unwrap.type\": \"io.debezium.transforms.ExtractNewRecordState\", \"transforms.unwrap.drop.tombstones\": false, \"transforms.unwrap.delete.handling.mode\": \"rewrite\" } }";
    }

}
