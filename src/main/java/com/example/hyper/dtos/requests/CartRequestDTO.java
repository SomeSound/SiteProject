package com.example.hyper.dtos.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CartRequestDTO {

    private Long Id;

    @NotEmpty(message = "Invalid name, can not be empty" )
    private Long customerId;
}
