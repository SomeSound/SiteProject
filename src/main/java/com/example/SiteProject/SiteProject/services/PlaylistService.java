package com.example.SiteProject.SiteProject.services;

import com.example.SiteProject.SiteProject.dtos.PlaylistDTO;
import com.example.SiteProject.SiteProject.dtos.responses.PlaylistPageReponseDTO;
import com.example.SiteProject.SiteProject.dtos.responses.PlaylistReponseDTO;
import org.springframework.data.domain.Pageable;

public interface PlaylistService {

    public PlaylistReponseDTO save(PlaylistDTO playlist) throws Exception;

    public PlaylistPageReponseDTO find(String name, Pageable pageable);

    public void delete(Long id);

}
