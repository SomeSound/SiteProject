package br.com.hyper.dtos.responses;

import javax.validation.constraints.NotEmpty;

public class CartResponseDTO {

    private Long totalPrice;

    @NotEmpty(message = "Invalid name, can not be empty" )
    private Long totalItems;
}
