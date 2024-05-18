package br.com.hyper.services;

import br.com.hyper.dtos.requests.ArtistRequestDTO;
import br.com.hyper.dtos.requests.CartRequestDTO;
import br.com.hyper.dtos.responses.artist.ArtistResponseDTO;
import br.com.hyper.dtos.responses.artist.ArtistTrackResponseDTO;
import br.com.hyper.dtos.responses.pages.ArtistPageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ArtistService {

    ArtistResponseDTO save(ArtistRequestDTO artist);

    ArtistPageResponseDTO find(Pageable pageable);

    ArtistTrackResponseDTO findByUsername(String username);

    void delete(Long id);

    ArtistResponseDTO addCart(Long id, CartRequestDTO cart);
}
