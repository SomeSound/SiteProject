package com.example.hyper.dtos.requests;

import com.example.hyper.entities.CustomerEntity;
import com.example.hyper.entities.TrackEntity;
import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ReviewRequestDTO {

    private Long id;

    private float score;

    private CustomerEntity customerId;

    private TrackEntity trackId;
}