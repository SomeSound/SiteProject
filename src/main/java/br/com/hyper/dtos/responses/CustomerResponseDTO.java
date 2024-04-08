package br.com.hyper.dtos.responses;

import lombok.Data;

import java.util.List;

@Data
public class CustomerResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String birthDate;
    private SubscriptionResponseDTO subscription;
    private String country;
    private String avatar;
    private List<ArtistResponseDTO> artistProfiles;
}
