package com.example.hyper.dtos;

import javax.validation.constraints.NotEmpty;

public class EmailDTO {

    @NotEmpty(message = "Invalid name, can not be empty")
    private String name;

    @NotEmpty(message = "Invalid email, can not be empty")
    private String email;

    @NotEmpty(message = "Invalid text, can not be empty")
    private String text;

}
