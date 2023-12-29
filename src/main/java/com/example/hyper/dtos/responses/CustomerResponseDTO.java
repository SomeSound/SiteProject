package com.example.hyper.dtos.responses;

import lombok.Data;

@Data
public class CustomerResponseDTO {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String avatar;
}
