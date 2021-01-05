package com.training.repository;

import com.training.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {

    // get account by email and password
    @Query("SELECT a FROM Account a WHERE a.email = ?1 AND a.password = ?2")
    Optional<Account> findAccount(String email, String password);
}
