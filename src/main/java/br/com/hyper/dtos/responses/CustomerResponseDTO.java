package br.com.hyper.dtos.responses;

import lombok.Data;

@Data
public class CustomerResponseDTO {

    private Long id;
    private String name;
    private String customerId;
    private String email;
    private String birthDate;
    private SubscriptionResponseDTO subscription;
    private String country;
    private String avatar;
}
