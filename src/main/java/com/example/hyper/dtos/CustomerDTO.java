package com.example.hyper.dtos;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CustomerDTO {

    private Long id;

    private String avatar;

    @NotEmpty(message = "Invalid name, can not be empty")
    private String name;

    @NotEmpty(message = "Invalid username, can not be empty")
    private String username;

    @NotEmpty(message = "Invalid password, can not be empty")
    private String password;

    @NotEmpty(message = "Invalid email, can not be empty")
    private String email;

    @NotEmpty(message = "Invalid birthDate, can not be empty")
    private String birthDate;

    @NotEmpty(message = "Invalid subscription, can not be empty")
    private Long subscription;

    @NotEmpty(message = "Invalid country, can not be empty")
    private String country;
}
