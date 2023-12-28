package com.example.hyper.services;

import com.example.hyper.constants.ErrorCodes;
import com.example.hyper.dtos.requests.PlaylistRequestDTO;
import com.example.hyper.dtos.responses.PlaylistResponseDTO;
import com.example.hyper.dtos.responses.pages.PlaylistPageReponseDTO;
import com.example.hyper.entities.PlaylistEntity;
import com.example.hyper.exceptions.PlaylistNotFoundException;
import com.example.hyper.proxys.SpotifyProxy;
import com.example.hyper.repositories.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpotifyServiceImpl implements SpotifyService {

    @Autowired
    private SpotifyProxy spotifyProxy;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Paging<Track> findTracksByName(String name) {
        try {
            return spotifyProxy.findTrackByName(name);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Paging<AlbumSimplified> findAlbumsByName(String name) {
        try {
            return spotifyProxy.findAlbumByName(name);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Paging<Artist> findArtistsByName(String name) {
        try {
            return spotifyProxy.findArtistsByName(name);
        }catch (Exception e){
            return null;
        }
    }
}
