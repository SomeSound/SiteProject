package br.com.hyper.dtos.responses;

import lombok.Data;

@Data
public class TrackResponseDTO {

    private Long id;
    private String name;
    private Double duration;
    private String genre;
    private int price;
}
