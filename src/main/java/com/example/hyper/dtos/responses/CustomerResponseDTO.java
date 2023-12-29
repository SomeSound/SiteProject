package com.example.hyper.dtos.responses;

import lombok.Data;

@Data
public class CustomerResponseDTO {

    private Long id;
    private String name;
    private String customerId;
    private String username;
    private String email;
    private String birthDate;
    private int subscription;
    private String country;
    private String avatar;
}
