package com.example.SiteProject.SiteProject.services;

import com.example.SiteProject.SiteProject.dtos.ArtistDTO;
import com.example.SiteProject.SiteProject.dtos.responses.ArtistPageResponseDTO;
import com.example.SiteProject.SiteProject.dtos.responses.ArtistResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArtistService {

    public ArtistResponseDTO save(ArtistDTO artist);

    public ArtistPageResponseDTO find(List<String> names, Pageable pageable);

    public void delete(long id);
}
