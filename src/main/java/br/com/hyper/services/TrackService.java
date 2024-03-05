package br.com.hyper.services;

import br.com.hyper.dtos.requests.TrackRequestDTO;
import br.com.hyper.dtos.responses.pages.TrackPageResponseDTO;
import br.com.hyper.dtos.responses.AlbumResponseDTO;
import br.com.hyper.dtos.responses.TrackResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TrackService {

    AlbumResponseDTO save(TrackRequestDTO track);

    TrackPageResponseDTO find(List<String> genres, Pageable pageable);

    TrackResponseDTO update(Long id, TrackRequestDTO track);

    void delete(Long id);


}
