package br.com.hyper.dtos.responses;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartResponseDTO {

    private int totalItems;

    private BigDecimal totalPrice;

    private List<TrackCartResponseDTO> tracks;
}
