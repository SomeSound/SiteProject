package br.com.hyper.dtos.responses.artist;

import br.com.hyper.dtos.responses.CartResponseDTO;
import br.com.hyper.dtos.responses.track.TrackResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class ArtistTrackResponseDTO {

    private String username;

    private List<TrackResponseDTO> tracks;
}
