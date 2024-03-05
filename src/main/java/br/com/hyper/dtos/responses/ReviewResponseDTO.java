package br.com.hyper.dtos.responses;

import br.com.hyper.entities.CustomerEntity;
import br.com.hyper.entities.TrackEntity;
import lombok.Data;

@Data
public class ReviewResponseDTO {
    private Long id;

    private float score;

    private CustomerEntity customerId;

    private TrackEntity trackId;
}
