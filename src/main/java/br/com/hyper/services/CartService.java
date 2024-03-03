package br.com.hyper.services;

import br.com.hyper.dtos.requests.CartRequestDTO;
import br.com.hyper.dtos.responses.CartResponseDTO;
import br.com.hyper.dtos.responses.pages.CartPageResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CartService {
    CartResponseDTO save(CartRequestDTO cart);

    CartPageResponseDTO find(Long customerId, Pageable pageable);

    CartResponseDTO update(Long id, CartRequestDTO artist);

    void delete(Long id);
}
