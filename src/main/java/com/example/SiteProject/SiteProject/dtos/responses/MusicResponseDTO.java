package com.example.SiteProject.SiteProject.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class MusicResponseDTO {

    private Long id;
    private String name;
    private String duration;
}
