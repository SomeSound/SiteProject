package br.com.hyper.dtos.responses;

import lombok.Data;

@Data
public class ArtistResponseDTO {

    private Long id;

    private String username;

    private CustomerResponseDTO customer;

    private int credits;
}
