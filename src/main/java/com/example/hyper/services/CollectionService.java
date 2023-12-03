package com.example.hyper.services;

import com.example.hyper.dtos.requests.ArtistRequestDTO;
import com.example.hyper.dtos.requests.CollectionRequestDTO;
import com.example.hyper.dtos.responses.ArtistResponseDTO;
import com.example.hyper.dtos.responses.CollectionResponseDTO;
import com.example.hyper.dtos.responses.pages.ArtistPageResponseDTO;
import com.example.hyper.dtos.responses.pages.CollectionPageResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CollectionService {

    public CollectionService save(CollectionRequestDTO collection);

    public CollectionPageResponseDTO find(String collection, Pageable pageable);

    public CollectionResponseDTO update(Long id, CollectionRequestDTO collection);
    public void delete(Long id);

}
