package com.example.SiteProject.SiteProject.dtos;

import com.example.SiteProject.SiteProject.entities.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserDTO extends BaseEntity {

    private Long id;

    private String avatar;

    @NotEmpty(message = "Invalid name, can not be empty")
    private String name;

    private String username;

    @NotEmpty(message = "Invalid email, can not be empty")
    private String email;

    private String credits;
}
