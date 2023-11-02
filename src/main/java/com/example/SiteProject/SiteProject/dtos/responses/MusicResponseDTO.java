package com.example.SiteProject.SiteProject.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
public class MusicResponseDTO {

    private Long id;
    private String name;
    private Double duration;
    private String genre;
}
