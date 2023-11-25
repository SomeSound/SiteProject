package com.example.SiteProject.SiteProject.dtos.responses;

import com.example.SiteProject.SiteProject.dtos.PlaylistDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PlaylistPageReponseDTO {
    private boolean last;

    private boolean first;

    private Integer totalElements;

    private Integer totalPages;

    private Integer size;

    private Integer numberOfElements;

    @JsonProperty("page")
    private Integer number;

    @JsonProperty("playlistDataList")
    private List<PlaylistDTO> content;
}
