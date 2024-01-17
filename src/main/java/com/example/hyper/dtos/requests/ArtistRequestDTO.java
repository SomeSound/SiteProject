package com.example.hyper.dtos.requests;

import lombok.Data;
import javax.validation.constraints.NotEmpty;

@Data
public class ArtistRequestDTO {

    private Long id;

    @NotEmpty(message = "Invalid name, can not be empty" )
    private String name;

    @NotEmpty(message = "Invalid country, can not be empty")
    private String country;

    @NotEmpty(message = "Invalid username, can not be empty")
    private String username;

}
