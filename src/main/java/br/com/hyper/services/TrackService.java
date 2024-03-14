package br.com.hyper.services;

import br.com.hyper.dtos.requests.TrackRequestDTO;
import br.com.hyper.dtos.responses.pages.TrackPageResponseDTO;
import br.com.hyper.dtos.responses.AlbumResponseDTO;
import br.com.hyper.dtos.responses.TrackResponseDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TrackService {

    TrackResponseDTO save(TrackRequestDTO track, MultipartFile file, Long artistId);

    TrackPageResponseDTO find(List<String> genres, Pageable pageable);

    TrackResponseDTO update(Long id, TrackRequestDTO track);

    void delete(Long id);

    byte[] downloadTrack(Long id);


}
