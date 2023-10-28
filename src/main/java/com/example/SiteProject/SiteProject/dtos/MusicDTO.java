package com.example.SiteProject.SiteProject.dtos;

import com.example.SiteProject.SiteProject.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MusicDTO extends BaseEntity {

    private String name;
    private String duration;

}
