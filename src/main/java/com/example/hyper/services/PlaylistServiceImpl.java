package com.example.hyper.services;

import com.example.hyper.constants.ErrorCodes;
import com.example.hyper.dtos.responses.PlaylistPageReponseDTO;
import com.example.hyper.dtos.responses.PlaylistReponseDTO;
import com.example.hyper.exceptions.MusicNotFoundException;
import com.example.hyper.dtos.PlaylistDTO;
import com.example.hyper.entities.PlaylistEntity;
import com.example.hyper.repositories.PlaylistRepository;
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

    private final PlaylistRepository playlistRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public PlaylistReponseDTO save(PlaylistDTO playlist) throws Exception {

        PlaylistEntity playlistEntity;
        try{
            playlistEntity = modelMapper.map(playlist, PlaylistEntity.class);

            playlistEntity = playlistRepository.save(playlistEntity);

            return modelMapper.map(playlistEntity, PlaylistReponseDTO.class);

        }catch (DataIntegrityViolationException e){
            throw new Exception(e);
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
    public void delete(Long id) {
        PlaylistEntity playlistCurrent = findByIdOrThrowMusicDataNotFoundException(id);

        playlistRepository.delete(playlistCurrent);
    }

    private PlaylistEntity findByIdOrThrowMusicDataNotFoundException(Long id) {
        return playlistRepository.findById(id).orElseThrow(
                () -> new MusicNotFoundException(ErrorCodes.DATA_NOT_FOUND, ErrorCodes.DATA_NOT_FOUND.getMessage()));
    }
}
