package com.training.service;

import com.training.model.UserLogin;

import java.util.Optional;

public interface UserLoginService {

    // get userLogin
    Optional<UserLogin> findUser(String email, String password);
}
