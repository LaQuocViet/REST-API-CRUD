package com.training.repository;

import com.training.model.UserLogin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLoginRepository extends CrudRepository<UserLogin, String> {

    // return a user
    @Query("Select u from UserLogin u WHERE u.email = ?1 AND u.password = ?2")
    Optional<UserLogin> findUser(String email, String password);
}
