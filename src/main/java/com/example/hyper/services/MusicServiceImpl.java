package com.example.hyper.services;

import com.example.hyper.constants.ErrorCodes;
import com.example.hyper.dtos.responses.MusicResponseDTO;
import com.example.hyper.exceptions.MusicNotFoundException;
import com.example.hyper.dtos.MusicDTO;
import com.example.hyper.dtos.responses.MusicPageResponseDTO;
import com.example.hyper.entities.MusicEntity;
import com.example.hyper.repositories.MusicRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MusicServiceImpl implements MusicService {

    private final MusicRepository musicRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public MusicResponseDTO save(MusicDTO music) {

        MusicEntity musicEntity;
        try{
            musicEntity = modelMapper.map(music, MusicEntity.class);

            musicEntity = musicRepository.save(musicEntity);

            return modelMapper.map(musicEntity, MusicResponseDTO.class);

        }catch (DataIntegrityViolationException e){
            return null; // Implementar exception
        }

    }

    @Override
    public MusicPageResponseDTO find(List<String> genres, Pageable pageable) {

        Page<MusicEntity> musicEntities;

        if(genres != null){
            musicEntities = musicRepository.findByGenre(genres, pageable);
        } else {
            musicEntities = musicRepository.findAll(pageable);
        }

        return modelMapper.map(musicEntities, MusicPageResponseDTO.class);
    }

    @Override
    public MusicResponseDTO update(Long id, MusicDTO music) {
        MusicEntity musicCurrent = findByIdOrThrowMusicDataNotFoundException(id);

        musicCurrent.setName(music.getName());
        musicCurrent.setTime(music.getDuration());

        musicRepository.save(musicCurrent);

        return modelMapper.map(musicCurrent, MusicResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        MusicEntity musicCurrent = findByIdOrThrowMusicDataNotFoundException(id);

        musicRepository.delete(musicCurrent);
    }

    private MusicEntity findByIdOrThrowMusicDataNotFoundException(Long id) {
        return musicRepository.findById(id).orElseThrow(
                () -> new MusicNotFoundException(ErrorCodes.DATA_NOT_FOUND, ErrorCodes.DATA_NOT_FOUND.getMessage()));
    }

}