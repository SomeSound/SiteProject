package com.example.hyper.dtos.responses;

import com.example.hyper.entities.CustomerEntity;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.ZonedDateTime;

@Data

public class FollowResponseDTO {
    private Long id;
    private CustomerEntity customerId;
    private CustomerEntity followingId;
    private ZonedDateTime followDate;
}
