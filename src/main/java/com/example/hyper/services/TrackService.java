package com.example.hyper.services;

import com.example.hyper.dtos.responses.TrackResponseDTO;
import com.example.hyper.dtos.requests.TrackRequestDTO;
import com.example.hyper.dtos.responses.pages.TrackPageResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TrackService {

    public TrackResponseDTO save(TrackRequestDTO track);

    public TrackPageResponseDTO find(List<String> genres, Pageable pageable);

    public TrackResponseDTO update(Long id, TrackRequestDTO music);

    public void delete(Long id);


}
