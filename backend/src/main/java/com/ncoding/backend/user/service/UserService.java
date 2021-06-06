package com.ncoding.backend.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface UserService<T, R> {
    Page<T> findAll(Pageable pageable);

    T registerNewUser(R userRequest);

    T doLogin(R userRequest);
}
