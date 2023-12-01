package com.example.hyper.dtos;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class PlaylistDTO {
    private Long id;

    @NotEmpty(message = "Invalid name, can not be empty")
    private String name;

//    private List<MusicDTO> playlist;
}
