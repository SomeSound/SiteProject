package com.example.hyper.services;

import com.example.hyper.constants.ErrorCodes;
import com.example.hyper.dtos.responses.TrackResponseDTO;
import com.example.hyper.dtos.responses.pages.PlaylistPageReponseDTO;
import com.example.hyper.dtos.responses.PlaylistResponseDTO;
import com.example.hyper.entities.TrackEntity;
import com.example.hyper.exceptions.PlaylistNotFoundException;
import com.example.hyper.exceptions.TrackNotFoundException;
import com.example.hyper.dtos.requests.PlaylistRequestDTO;
import com.example.hyper.entities.PlaylistEntity;
import com.example.hyper.repositories.PlaylistRepository;
import com.example.hyper.repositories.TrackRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    TrackRepository trackRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PlaylistResponseDTO save(PlaylistRequestDTO playlist) {

        PlaylistEntity playlistEntity;
        try{
            playlistEntity = modelMapper.map(playlist, PlaylistEntity.class);

            playlistEntity = playlistRepository.save(playlistEntity);

            return modelMapper.map(playlistEntity, PlaylistResponseDTO.class);

        }catch (DataIntegrityViolationException e){
            return null; // Implementar erro
        }
    }

    @Override
    public PlaylistPageReponseDTO find(String name, Pageable pageable) {

        Page<PlaylistEntity> playlistEntities;

        if(name != null){
            playlistEntities = playlistRepository.findByName(name, pageable);
        } else {
            playlistEntities = playlistRepository.findAll(pageable);
        }

        return modelMapper.map(playlistEntities, PlaylistPageReponseDTO.class);
    }

    @Override
    public PlaylistResponseDTO updateName(Long id, String name) {
        PlaylistEntity playlist = findByIdOrThrowPlaylistDataNotFoundException(id);

        playlist.setName(name);

        playlistRepository.save(playlist);

        return modelMapper.map(playlist, PlaylistResponseDTO.class);
    }

    @Override
    public PlaylistResponseDTO addTrack(Long id, Long trackId) {
        PlaylistEntity playlist = findByIdOrThrowPlaylistDataNotFoundException(id);
        TrackEntity track = findByIdOrThrowTrackDataNotFoundException(id);

        playlist.getTrackList().add(track);

        return modelMapper.map(playlist, PlaylistResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        PlaylistEntity playlistCurrent = findByIdOrThrowPlaylistDataNotFoundException(id);

        playlistRepository.delete(playlistCurrent);
    }

    private PlaylistEntity findByIdOrThrowPlaylistDataNotFoundException(Long id) {
        return playlistRepository.findById(id).orElseThrow(
                () -> new PlaylistNotFoundException(ErrorCodes.DATA_NOT_FOUND, ErrorCodes.DATA_NOT_FOUND.getMessage()));
    }

    private TrackEntity findByIdOrThrowTrackDataNotFoundException(Long id) {
        return trackRepository.findById(id).orElseThrow(
                () -> new TrackNotFoundException(ErrorCodes.DATA_NOT_FOUND, ErrorCodes.DATA_NOT_FOUND.getMessage()));
    }
}
