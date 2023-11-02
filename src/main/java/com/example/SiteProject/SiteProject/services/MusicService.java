package com.example.SiteProject.SiteProject.services;

import com.example.SiteProject.SiteProject.dtos.MusicDTO;
import com.example.SiteProject.SiteProject.dtos.responses.MusicPageResponseDTO;
import com.example.SiteProject.SiteProject.dtos.responses.MusicResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MusicService {

    public MusicResponseDTO save(MusicDTO music);

    public MusicPageResponseDTO findByGenre(List<String> genres, String name, Pageable pageable);

    public MusicResponseDTO update(Long id, MusicDTO music);

    public void delete(Long id);


}
