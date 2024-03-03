package br.com.hyper.dtos.requests;

import br.com.hyper.entities.CustomerEntity;
import br.com.hyper.entities.TrackEntity;
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