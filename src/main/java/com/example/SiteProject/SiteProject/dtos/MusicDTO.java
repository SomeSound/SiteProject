package com.example.SiteProject.SiteProject.dtos;

import com.example.SiteProject.SiteProject.entities.BaseEntity;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

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
