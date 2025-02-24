package br.com.hyper.services;

import br.com.hyper.dtos.requests.CartRequestDTO;
import br.com.hyper.dtos.responses.CartResponseDTO;
import br.com.hyper.dtos.responses.pages.CartPageResponseDTO;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;

public interface CartService {
    CartResponseDTO save(@Valid CartRequestDTO cart);

    CartPageResponseDTO findAll(Pageable pageable);

    CartResponseDTO update(Long id, CartRequestDTO artist);

    void delete(Long id);

    CartResponseDTO addTrack(Long cartId, Long trackId);
}
