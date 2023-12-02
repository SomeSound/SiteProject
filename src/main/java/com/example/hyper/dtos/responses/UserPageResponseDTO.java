package com.example.hyper.dtos.responses;

import com.example.hyper.dtos.requests.UserRequestDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserPageResponseDTO {

    private boolean last;

    private boolean first;

    private Integer totalElements;

    private Integer totalPages;

    private Integer size;

    private Integer numberOfElements;

    @JsonProperty("page")
    private Integer number;

    @JsonProperty("userDataList")
    private List<UserRequestDTO> content;
}