package br.com.hyper.dtos.responses.track;

import lombok.Data;

@Data
public class TrackCartResponseDTO {

    private Long id;
    private String name;
    private Double duration;
    private String genre;
    private String image;
    private int price;
}
