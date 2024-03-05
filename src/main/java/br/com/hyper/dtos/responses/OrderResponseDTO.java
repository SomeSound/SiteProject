package br.com.hyper.dtos.responses;

import br.com.hyper.entities.CustomerEntity;

import java.math.BigDecimal;

public class OrderResponseDTO {

    private Long id;

    private int totalItems;

    private BigDecimal totalPrice;

    private CustomerEntity customerId;
}
