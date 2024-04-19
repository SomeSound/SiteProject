package br.com.hyper.dtos.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CartRequestDTO {

    private Long id;

    private String name;
}
