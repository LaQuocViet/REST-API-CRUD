package com.training.controller;

import com.training.model.User;
import com.training.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
@Api(tags = "User Controller")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Get all users", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "not found")
    })
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = new ArrayList<>();

        userService.getAllUser().forEach(user -> {
            users.add(user);
        });
        return ResponseEntity.ok(users);
    }

    @ApiOperation(value = "Get user by id", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 204, message = "no content"),
            @ApiResponse(code = 404, message = "not found")
    })
    @GetMapping("/users/{id}")
    public ResponseEntity<User> findUserById(@Valid @PathVariable("id") Integer id) {
        Optional<User> userData = userService.findUserById(id);

        if (userData.isPresent()) {
            return ResponseEntity.ok(userData.get());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Post a user", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 400, message = "BAD_REQUEST")
    })
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        try {
            User user1 = userService.saveUser(new User(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getBirthDate(),
                    user.isSex(),
                    user.getAddress(),
                    user.getEmail(),
                    user.getJob(),
                    user.getPhone(),
                    user.getMaritalStatus()));
            return new ResponseEntity<>(user1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @ApiOperation(value = "Edit a user", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 204, message = "no content"),
            @ApiResponse(code = 404, message = "not found"),
            @ApiResponse(code = 500, message = "INTERNAL_SERVER_ERROR")
    })
    @PutMapping("/users/{id}")
    public ResponseEntity<User> update(@Valid @PathVariable("id") Integer id, @RequestBody User user) {
        Optional<User> userData = userService.findUserById(id);

        if (userData.isPresent()) {
            user.setId(id);
            return ResponseEntity.ok(userService.saveUser(user));
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "delete all user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 500, message = "INTERNAL_SERVER_ERROR")
    })
    @DeleteMapping("/users")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        try {
            userService.deleteAllUsers();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "delete a user by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 500, message = "INTERNAL_SERVER_ERROR"),
            @ApiResponse(code = 204, message = "No CONTENT")
    })
    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@Valid @PathVariable("id") Integer id) {
        Optional<User> userData = userService.findUserById(id);
        if (userData.isPresent()) {
            userService.deleteUserById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
