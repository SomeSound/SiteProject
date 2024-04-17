package br.com.hyper.dtos.responses;

import br.com.hyper.entities.CustomerEntity;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class FollowResponseDTO {
    private Long id;
    private CustomerEntity followingId;
    private ZonedDateTime followDate;
}
