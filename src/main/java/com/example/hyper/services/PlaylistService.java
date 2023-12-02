package com.example.hyper.services;

import com.example.hyper.dtos.responses.PlaylistPageReponseDTO;
import com.example.hyper.dtos.responses.PlaylistReponseDTO;
import com.example.hyper.dtos.PlaylistDTO;
import org.springframework.data.domain.Pageable;

public interface PlaylistService {

    public PlaylistReponseDTO save(PlaylistDTO playlist);

    public PlaylistPageReponseDTO find(String name, Pageable pageable);

    public void delete(Long id);

}
