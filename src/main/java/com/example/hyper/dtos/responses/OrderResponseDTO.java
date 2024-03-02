package com.example.hyper.dtos.responses;

import com.example.hyper.entities.CustomerEntity;

import java.math.BigDecimal;

public class OrderResponseDTO {

    private Long id;

    private int totalItems;

    private BigDecimal totalPrice;

    private CustomerEntity customerId;
}
