package com.example.hyper.dtos;

import com.example.hyper.entities.BaseEntity;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@EqualsAndHashCode(callSuper = true)
@Data
public class MusicDTO extends BaseEntity {

    private Long id;

    @NotEmpty(message = "Invalid name, can not be empty")
    private String name;

    @NotEmpty(message = "Invalid duration, can not be empty")
    private Double duration;

    @NotEmpty(message = "Invalid genre, can not be empty")
    private String genre;

}
