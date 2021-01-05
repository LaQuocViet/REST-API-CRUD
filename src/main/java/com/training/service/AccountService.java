package com.training.service;

import com.training.model.Account;

import java.util.Optional;

public interface AccountService {

    // get account by email and password
    Optional<Account> findAccount(String email, String password);
}
