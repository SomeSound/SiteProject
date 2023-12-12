package com.example.hyper.dtos.requests;

import com.example.hyper.entities.TrackEntity;

import javax.validation.constraints.NotEmpty;

public class ReviewRequestDTO {

    @NotEmpty(message = "Invalid track, can not be empty")
    private TrackEntity track;

}
