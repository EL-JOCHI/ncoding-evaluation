package com.ncoding.backend.user.service;

import com.ncoding.backend.user.controller.request.UserRequest;
import com.ncoding.backend.user.domain.User;
import com.ncoding.backend.user.persistence.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Log4j2
@Service
@Primary
public class UserServiceImpl implements UserService<User, UserRequest> {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User registerNewUser(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return userRepository.save(createUserFromUserRequest(userRequest));
    }

    private User createUserFromUserRequest(UserRequest userRequest) {
        return User.builder()
                .email(userRequest.getEmail())
                .password(encoder.encode(userRequest.getPassword()))
                .build();
    }

    @Override
    public User doLogin(UserRequest userRequest) {
        User user = userRepository.findByEmail(userRequest.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!isUserPasswordMatch(user, userRequest.getPassword())) {
            log.error("The given password does not match.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        user.setLastLogin(new Date());
        log.info(String.format("User [%s] is now logged", user));
        return userRepository.save(user);
    }

    private boolean isUserPasswordMatch(User user, String password) {
        return encoder.matches(password, user.getPassword());
    }

}
