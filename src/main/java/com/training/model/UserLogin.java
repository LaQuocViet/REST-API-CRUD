package com.training.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_login")
public class UserLogin {
    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne
    @JoinColumn (name = "userID")
    User users;

    public UserLogin() {

    }

    public UserLogin(String email, String password) {
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
