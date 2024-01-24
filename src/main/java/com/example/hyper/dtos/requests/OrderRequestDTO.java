package com.example.hyper.dtos.requests;

import com.example.hyper.entities.CustomerEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderRequestDTO {

    private Long id;

    private int totalItems;

    private BigDecimal totalPrice;

    private CustomerEntity customerId;
}
