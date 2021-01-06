package com.training.controller;

import com.training.model.Account;
import com.training.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
@Api(tags = "Account Controller")
public class AccountController {

    @Autowired
    private AccountService service;


    @ApiOperation(value = "Login")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 204, message = "no content"),
            @ApiResponse(code = 404, message = "not found")
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody Account account) {
        if (account != null) {
            Optional<Account> accountData = service.findAccount(account.getEmail(), account.getPassword());
            if (accountData.isPresent()) {
                return ResponseEntity.ok(accountData.get().getEmail() + "-" + accountData.get().getPassword());
            }
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }
//
//    @GetMapping("/hello")
//    public String hello () {
//        return "Hello";
//    }
}
