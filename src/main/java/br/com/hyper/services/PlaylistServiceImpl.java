package br.com.hyper.services;

import br.com.hyper.constants.ErrorCodes;
import br.com.hyper.dtos.requests.PlaylistRequestDTO;
import br.com.hyper.dtos.responses.PlaylistResponseDTO;
import br.com.hyper.dtos.responses.pages.PlaylistPageReponseDTO;
import br.com.hyper.entities.PlaylistEntity;
import br.com.hyper.entities.TrackEntity;
import br.com.hyper.exceptions.PlaylistNotFoundException;
import br.com.hyper.exceptions.TrackNotFoundException;
import br.com.hyper.repositories.TrackRepository;
import br.com.hyper.repositories.PlaylistRepository;
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
    private final TrackRepository trackRepository;

    @Autowired
    private final ModelMapper modelMapper;

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
    public PlaylistResponseDTO update(Long id, PlaylistRequestDTO playlist) {
        PlaylistEntity playlistCurrent = findByIdOrThrowPlaylistDataNotFoundException(id);

        playlistCurrent.setName(playlist.getName());

        playlistRepository.save(playlistCurrent);

        return modelMapper.map(playlistCurrent, PlaylistResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        PlaylistEntity playlistCurrent = findByIdOrThrowPlaylistDataNotFoundException(id);

        playlistRepository.delete(playlistCurrent);
    }

    @Override
    public PlaylistResponseDTO addTrack(Long id, Long trackId) {
        PlaylistEntity playlist = findByIdOrThrowPlaylistDataNotFoundException(id);
        TrackEntity track = findByIdOrThrowTrackDataNotFoundException(id);
        playlist.getTracks().add(track);
        return modelMapper.map(playlist, PlaylistResponseDTO.class);
    }

    @Override
    public PlaylistResponseDTO updateName(Long id, String name) {
        PlaylistEntity playlist = findByIdOrThrowPlaylistDataNotFoundException(id);

        playlist.setName(name);

        playlistRepository.save(playlist);

        return modelMapper.map(playlist, PlaylistResponseDTO.class);
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
