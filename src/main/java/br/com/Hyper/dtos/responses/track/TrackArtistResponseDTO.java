package br.com.hyper.dtos.responses.track;

import br.com.hyper.dtos.responses.artist.ArtistResponseDTO;
import lombok.Data;

@Data
public class TrackArtistResponseDTO {

    private Long id;
    private String name;
    private Double duration;
    private String genre;
    private String image;
    private int price;
    private ArtistResponseDTO artist;
}
