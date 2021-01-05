package com.training.service;

import com.training.model.Account;
import com.training.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repository;

    @Override
    public Optional<Account> findAccount(String email, String password) {
        return repository.findAccount(email, password);
    }
}
