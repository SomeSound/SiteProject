package br.com.hyper.dtos.responses;

import br.com.hyper.dtos.responses.track.TrackCartResponseDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartResponseDTO {

    private Long id;
    private String name;
    private int totalItems;
    private BigDecimal totalPrice;
    private List<TrackCartResponseDTO> tracks;
}
