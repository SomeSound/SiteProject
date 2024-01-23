package com.example.hyper.services;

import com.example.hyper.dtos.responses.AlbumResponseDTO;
import com.example.hyper.dtos.responses.TrackResponseDTO;
import com.example.hyper.dtos.requests.TrackRequestDTO;
import com.example.hyper.dtos.responses.pages.TrackPageResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TrackService {

    AlbumResponseDTO save(TrackRequestDTO track);

    TrackPageResponseDTO find(List<String> genres, Pageable pageable);

    TrackResponseDTO update(Long id, TrackRequestDTO track);

    void delete(Long id);


}
