package com.example.hyper.dtos;

import com.example.SiteProject.SiteProject.entities.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Data
public class ArtistDTO extends BaseEntity {

    private Long id;
    @NotEmpty(message = "Invalid name, can not be empty" )
    private String name;

    @NotEmpty(message = "Invalid country, can not be empty")
    private String country;
}
