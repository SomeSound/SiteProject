package br.com.hyper.dtos.responses;

import br.com.hyper.entities.CustomerEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderResponseDTO {

    private Long id;

    private int totalItems;

    private BigDecimal totalPrice;

}
