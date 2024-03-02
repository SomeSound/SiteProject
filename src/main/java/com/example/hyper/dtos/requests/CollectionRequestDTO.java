package com.example.hyper.dtos.requests;

import com.example.hyper.entities.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CollectionRequestDTO extends BaseEntity {

    @NotEmpty(message = "Invalid name, can not be empty" )
    private String name;

    //@NotEmpty(message = "Invalid playlist, can not be empty")
    //private List<playlists> playlists;
}
