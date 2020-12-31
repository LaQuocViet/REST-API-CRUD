package com.training.controller;

import com.training.interceptor.LoginInterceptor;
import com.training.model.UserLogin;
import com.training.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class UserLoginController {

    @Autowired
    private UserLoginService service;

    @PostMapping("/login")
    public ResponseEntity<?> createToken( @RequestBody UserLogin userLogin) {
        Optional<UserLogin> userData = service.findUser(userLogin.getEmail(), userLogin.getPassword());
        if (userData.isPresent()) {
            String tokenAuthentication = userData.get().getEmail() + "-" + userData.get().getPassword();
            return ResponseEntity.ok(tokenAuthentication);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
