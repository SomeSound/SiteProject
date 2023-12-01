package com.example.hyper.services;

import com.example.hyper.dtos.responses.MusicResponseDTO;
import com.example.hyper.dtos.MusicDTO;
import com.example.hyper.dtos.responses.MusicPageResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MusicService {

    public MusicResponseDTO save(MusicDTO music);

    public MusicPageResponseDTO find(List<String> genres, Pageable pageable);

    public MusicResponseDTO update(Long id, MusicDTO music);

    public void delete(Long id);


}
