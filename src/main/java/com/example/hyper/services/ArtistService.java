package com.example.hyper.services;

import com.example.hyper.dtos.requests.ArtistRequestDTO;
import com.example.hyper.dtos.responses.pages.ArtistPageResponseDTO;
import com.example.hyper.dtos.responses.ArtistResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArtistService {

    ArtistResponseDTO save(String customerId, ArtistRequestDTO artist);

    ArtistPageResponseDTO find(List<String> names, Pageable pageable);

    void delete(Long id);
}
