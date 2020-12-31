package com.training.service;

import com.training.model.UserLogin;
import com.training.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserLoginServiceImpl implements UserLoginService{

    @Autowired
    private UserLoginRepository repository;

    @Override
    public Optional<UserLogin> findUser(String email, String password) {
        return repository.findUser(email, password);
    }
}
