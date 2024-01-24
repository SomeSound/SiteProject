package com.example.hyper.dtos.responses.pages;

import com.example.hyper.dtos.responses.OrderResponseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OrderPageResponseDTO {
    private boolean last;

    private boolean first;

    private Integer totalElements;

    private Integer totalPages;

    private Integer size;

    private Integer numberOfElements;

    @JsonProperty("page")
    private Integer number;

    @JsonProperty("playlistDataList")
    private List<OrderResponseDTO> content;
}
