package com.example.hyper.dtos.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CustomerRequestDTO {

    private Long Id;

    private String avatar;

    @NotEmpty(message = "Invalid name, can not be empty")
    private String name;

    private String username;

    @NotEmpty(message = "Invalid email, can not be empty")
    private String email;

    private String credits;
}
