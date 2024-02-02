package com.example.hyper.dtos.responses;

import com.example.hyper.entities.CustomerEntity;
import com.example.hyper.entities.TrackEntity;
import lombok.Data;

@Data
public class ReviewResponseDTO {
    private Long id;

    private float score;

    private CustomerEntity customerId;

    private TrackEntity trackId;
}
