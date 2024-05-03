package br.com.hyper.dtos.responses;

import lombok.Data;

import java.util.List;

@Data
public class ArtistResponseDTO {

    private Long id;

    private String username;

    private int credits;

    private List<CartResponseDTO> carts;
}
