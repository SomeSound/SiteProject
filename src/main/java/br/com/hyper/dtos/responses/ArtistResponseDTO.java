package br.com.hyper.dtos.responses;

import lombok.Data;

@Data
public class ArtistResponseDTO {
    private Long id;
    private CustomerResponseDTO customer;
    private int credits;
}
