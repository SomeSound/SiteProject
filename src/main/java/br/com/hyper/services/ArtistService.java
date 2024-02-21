package br.com.hyper.services;

import br.com.hyper.dtos.responses.pages.ArtistPageResponseDTO;
import br.com.hyper.dtos.requests.ArtistRequestDTO;
import br.com.hyper.dtos.responses.ArtistResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArtistService {

    ArtistResponseDTO save(String customerId, ArtistRequestDTO artist);

    ArtistPageResponseDTO find(List<String> names, Pageable pageable);

    void delete(Long id);
}
