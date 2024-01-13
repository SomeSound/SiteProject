package com.example.hyper.dtos.requests;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RecordRequestDTO {

    @NotEmpty(message = "Invalid name, can not be empty")
    private String name;

    @NotEmpty(message = "Invalid country, can not be empty")
    private String country;

    private String description;

}
