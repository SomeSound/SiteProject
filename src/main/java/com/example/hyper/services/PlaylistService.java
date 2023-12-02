package com.example.hyper.services;

import com.example.hyper.dtos.responses.pages.PlaylistPageReponseDTO;
import com.example.hyper.dtos.responses.PlaylistResponseDTO;
import com.example.hyper.dtos.requests.PlaylistRequestDTO;
import org.springframework.data.domain.Pageable;

public interface PlaylistService {

    public PlaylistResponseDTO save(PlaylistRequestDTO playlist);

    public PlaylistPageReponseDTO find(String name, Pageable pageable);

    public PlaylistResponseDTO update(Long id, PlaylistRequestDTO playlist);

    public void delete(Long id);

}
