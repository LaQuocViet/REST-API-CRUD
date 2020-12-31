package com.training.controller;

import com.training.model.User;
import com.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity getAllUsers(@RequestParam String token) {
        List<User> users = new ArrayList<>();

        userService.getAllUser().forEach(user -> {
            users.add(user);
        });

        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(users);

    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") Integer id) {
        Optional<User> userData = userService.findUserById(id);

        if (userData.isPresent()) {
            return ResponseEntity.ok(userData.get());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        try{
            User _user = userService.saveUser(new User(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getBirthDate(),
                    user.isSex(),
                    user.getAddress(),
                    user.getEmail(),
                    user.getJob(),
                    user.getPhone(),
                    user.getMaritalStatus()));
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> update (@PathVariable("id") Integer id, @RequestBody User user) {
        Optional<User> userData =  userService.findUserById(id);

        if (userData.isPresent()) {
            user.setId(id);
            return ResponseEntity.ok(userService.saveUser(user));
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/users")
    public ResponseEntity<HttpStatus> deleteAllUsers(){
        try {
            userService.deleteAllUsers();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUserById (@PathVariable("id") Integer id) {
        Optional<User> userData = userService.findUserById(id);
        if (userData.isPresent()) {
            userService.deleteUserById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
