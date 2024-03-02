package com.example.hyper.services;

import com.example.hyper.dtos.requests.CartRequestDTO;
import com.example.hyper.dtos.responses.CartResponseDTO;
import com.example.hyper.dtos.responses.pages.CartPageResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CartService {
    public CartResponseDTO save(CartRequestDTO cart);

    public CartPageResponseDTO find(List<String> names, Pageable pageable);

    public CartResponseDTO update(Long id, CartRequestDTO artist);
    public void delete(Long id);
}
