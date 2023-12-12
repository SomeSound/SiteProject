package com.example.hyper.dtos.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
<<<<<<<< HEAD:src/main/java/com/example/hyper/dtos/requests/UserRequestDTO.java
public class UserRequestDTO extends BaseEntity {
========
public class CustomerDTO {

    private Long id;
>>>>>>>> master:src/main/java/com/example/hyper/dtos/CustomerDTO.java

    private String avatar;

    @NotEmpty(message = "Invalid name, can not be empty")
    private String name;

    private String username;

    @NotEmpty(message = "Invalid email, can not be empty")
    private String email;

    private String credits;
}
