package com.ncoding.backend.user.service;

import com.ncoding.backend.user.controller.request.UserRequest;
import com.ncoding.backend.user.domain.User;
import com.ncoding.backend.user.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        user.setLastLogin(new Date());
        return userRepository.save(user);
    }

    private boolean isUserPasswordMatch(User user, String password) {
        return encoder.matches(password, user.getPassword());
    }

}
