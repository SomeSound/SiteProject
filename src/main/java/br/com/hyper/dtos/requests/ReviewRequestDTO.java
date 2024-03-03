package br.com.hyper.dtos.requests;

import br.com.hyper.entities.CustomerEntity;
import br.com.hyper.entities.TrackEntity;
import lombok.Data;

@Data
public class ReviewRequestDTO {

    private Long id;

    private float score;

    private CustomerEntity customerId;

    private TrackEntity trackId;
}