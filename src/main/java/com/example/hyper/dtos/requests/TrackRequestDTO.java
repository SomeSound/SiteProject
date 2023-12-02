package com.example.hyper.dtos.requests;

import com.example.hyper.entities.BaseEntity;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
public class TrackRequestDTO extends BaseEntity {

    @NotEmpty(message = "Invalid name, can not be empty")
    private String name;

    @NotEmpty(message = "Invalid duration, can not be empty")
    private Double duration;

    @NotEmpty(message = "Invalid genre, can not be empty")
    private String genre;

}
