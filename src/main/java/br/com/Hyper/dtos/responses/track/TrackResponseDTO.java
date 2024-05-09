package br.com.hyper.dtos.responses.track;

import lombok.Data;

@Data
public class TrackResponseDTO {

    private Long id;
    private String name;
    private Double duration;
    private String genre;
    private String image;
    private int price;
    private TrackArtistResponseDTO artist;
}
