package com.example.SiteProject.SiteProject.services;

import com.example.SiteProject.SiteProject.dtos.ArtistDTO;
import com.example.SiteProject.SiteProject.dtos.MusicDTO;
import com.example.SiteProject.SiteProject.dtos.responses.ArtistPageResponseDTO;
import com.example.SiteProject.SiteProject.dtos.responses.ArtistResponseDTO;
import com.example.SiteProject.SiteProject.dtos.responses.MusicPageResponseDTO;
import com.example.SiteProject.SiteProject.dtos.responses.MusicResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArtistService {

    public ArtistResponseDTO save(ArtistDTO artist);

    public ArtistPageResponseDTO find(List<String> names, Pageable pageable);

    public ArtistResponseDTO update(Long id, ArtistDTO artist);
    public void delete(Long id);
}
