package com.training.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "user")
@ApiModel(value = "User model")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "Id auto increase upon create new")
    private int id;

    @Column(name = "first_Name", nullable = false)
    @NotBlank(message = "First name not blank")
    private String firstName;

    @Column(name = "last_Name", nullable = false)
    @NotBlank(message = "First name not blank")
    private String lastName;

    @Column(name = "birthdate", nullable = false)
    @NotBlank(message = "First name not blank")
    private String birthDate;

    @Column(name = "sex", nullable = false)
    private boolean sex;

    @Column(name = "address", nullable = false)
    @NotBlank(message = "First name not blank")
    private String address;

    @Column(name = "email", nullable = false)
    @NotBlank(message = "First name not blank")
    private String email;

    @Column(name = "job", nullable = false)
    @NotBlank(message = "First name not blank")
    private String job;

    @Column(name = "phone", nullable = false)
    @NotBlank(message = "First name not blank")
    @Pattern(regexp = "^0(1\\d{9}|9\\d{8})$")
    private String phone;

    @Column(name = "marital_Status", nullable = false)
    private int maritalStatus;

    public User() {

    }

    public User(String firstName, String lastName, String birthDate, boolean sex, String address, String email
            , String job, String phone, int maritalStatus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.sex = sex;
        this.address = address;
        this.email = email;
        this.job = job;
        this.phone = phone;
        this.maritalStatus = maritalStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(int maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
}
