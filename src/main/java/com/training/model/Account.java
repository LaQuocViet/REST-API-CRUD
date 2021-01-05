package com.training.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "account")
@ApiModel(value = "Account model")
public class Account {
    @Id
    @Column(name = "email", nullable = false)
    @ApiModelProperty(notes = "Email is primary key")
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    public Account() {

    }

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
