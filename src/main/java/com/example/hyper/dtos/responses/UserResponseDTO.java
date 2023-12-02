package com.example.hyper.dtos.responses;

import lombok.Data;

@Data
public class UserResponseDTO {

    private Long id;
    private String avatar;
    private String name;
    private String username;
    private String email;
    private String credits;
}
