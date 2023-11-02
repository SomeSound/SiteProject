package com.example.SiteProject.SiteProject.dtos.responses;

import com.example.SiteProject.SiteProject.dtos.MusicDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MusicPageResponseDTO {
    private boolean last;

    private boolean first;

    private Integer totalElements;

    private Integer totalPages;

    private Integer size;

    private Integer numberOfElements;

    @JsonProperty("page")
    private Integer number;

    @JsonProperty("musicDataList")
    private List<MusicDTO> content;
}
