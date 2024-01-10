package com.example.hyper.services;

import com.example.hyper.dtos.requests.AlbumRequestDTO;
import com.example.hyper.dtos.responses.AlbumResponseDTO;
import com.example.hyper.dtos.responses.pages.AlbumPageReponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AlbumService {

    public AlbumResponseDTO save(AlbumRequestDTO playlist);

    public AlbumPageReponseDTO find(List<String> name, Pageable pageable);

    public AlbumResponseDTO update(Long id, AlbumRequestDTO album);

    public void delete(Long id);
}
