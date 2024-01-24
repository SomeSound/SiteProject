package com.example.hyper.dtos.requests;

import com.example.hyper.entities.CustomerEntity;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.ZonedDateTime;

@Data
public class FollowRequestDTO {
    private Long id;

    @NotEmpty(message = "Invalid customer, can not be empty")
    private CustomerEntity customerId;

    @NotEmpty(message = "Invalid customer, can not be empty")
    private CustomerEntity followingId;

    private ZonedDateTime followDate;
}
