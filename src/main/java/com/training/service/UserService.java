package com.training.service;

import com.training.model.User;

import java.util.Optional;

public interface UserService {

    // get all user
    Iterable<User> getAllUser();

    // get all user by id
    Optional<User> findUserById(Integer id);

    // insert user
    User saveUser(User user);

    // delete user by id
    void deleteUserById(Integer id);

    // delete all users
    void deleteAllUsers();
}
