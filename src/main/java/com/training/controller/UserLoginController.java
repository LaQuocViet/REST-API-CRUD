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
    String tokenAuthentication;

    @Autowired
    private UserLoginService service;

    @PostMapping("/login")
    public ResponseEntity<?> createToken( @RequestBody UserLogin userLogin) {
        Optional<UserLogin> _userLogin = service.findUser(userLogin.getEmail(), userLogin.getPassword());
        if (_userLogin.isPresent()) {
            tokenAuthentication = _userLogin.get().getEmail() + "-" + _userLogin.get().getPassword();
            LoginInterceptor.tokenAuthentication = tokenAuthentication;
            System.out.println(LoginInterceptor.tokenAuthentication);
            return ResponseEntity.ok(tokenAuthentication);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/login/get")
    public ResponseEntity<HttpStatus> getData(@RequestParam String token) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
