package com.example.hyper.dtos;

import com.example.hyper.entities.BaseEntity;
import lombok.Data;
import javax.validation.constraints.NotEmpty;

@Data
public class ArtistDTO extends BaseEntity {

    private Long id;

    @NotEmpty(message = "Invalid name, can not be empty" )
    private String name;

    @NotEmpty(message = "Invalid country, can not be empty")
    private String country;
}
