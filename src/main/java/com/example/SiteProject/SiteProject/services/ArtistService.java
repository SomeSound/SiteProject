package com.example.SiteProject.SiteProject.services;

import com.example.SiteProject.SiteProject.dtos.ArtistDTO;
<<<<<<< HEAD

public interface ArtistService {

    ArtistDTO save(ArtistDTO artist);
=======
import com.example.SiteProject.SiteProject.dtos.responses.ArtistResponseDTO;

public interface ArtistService {

    public ArtistResponseDTO save(ArtistDTO artist);

    public void delete(long id);
>>>>>>> master
}
