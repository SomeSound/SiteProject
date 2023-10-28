package com.example.SiteProject.SiteProject.services;

import com.example.SiteProject.SiteProject.dtos.MusicDTO;
import com.example.SiteProject.SiteProject.dtos.responses.MusicResponseDTO;
import com.example.SiteProject.SiteProject.entities.MusicEntity;

public interface MusicService {

    public MusicResponseDTO save(MusicDTO music);
    public void delete(Long id);

}
