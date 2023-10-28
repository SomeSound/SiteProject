package com.example.SiteProject.SiteProject.dtos;

import com.example.SiteProject.SiteProject.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Data
public class MusicDTO extends BaseEntity {

    private Long id;

    @NotEmpty(message = "Invalid name, can not be empty")
    private String name;

    @NotNull
    private String duration;

}
