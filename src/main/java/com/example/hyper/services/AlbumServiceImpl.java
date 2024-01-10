package com.example.hyper.services;

import com.example.hyper.constants.ErrorCodes;
import com.example.hyper.dtos.requests.AlbumRequestDTO;
import com.example.hyper.dtos.responses.AlbumResponseDTO;
import com.example.hyper.dtos.responses.pages.AlbumPageReponseDTO;
import com.example.hyper.entities.AlbumEntity;
import com.example.hyper.exceptions.AlbumNotFoundException;
import com.example.hyper.repositories.AlbumRepository;
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
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AlbumResponseDTO save(AlbumRequestDTO album) {

        AlbumEntity albumEntity;
        try{
            albumEntity = modelMapper.map(album, AlbumEntity.class);

            albumEntity = albumRepository.save(albumEntity);

            return modelMapper.map(albumEntity, AlbumResponseDTO.class);

        }catch (DataIntegrityViolationException e){
            return null; // Implementar erro
        }
    }

    @Override
    public AlbumPageReponseDTO find(List<String> name, Pageable pageable) {

        Page<AlbumEntity> albumEntities;

        if(name != null){
            albumEntities = albumRepository.findByName(name, pageable);
        } else{
            albumEntities = albumRepository.findAll(pageable);
        }

        return modelMapper.map(albumEntities, AlbumPageReponseDTO.class);
    }


    @Override
    public AlbumResponseDTO update(Long id, AlbumRequestDTO album) {
        AlbumEntity albumCurrent = findByIdOrThrowAlbumDataNotFoundException(id);

        albumCurrent.setName(album.getName());

        albumRepository.save(albumCurrent);

        return modelMapper.map(albumCurrent, AlbumResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        AlbumEntity albumCurrent = findByIdOrThrowAlbumDataNotFoundException(id);

        albumRepository.delete(albumCurrent);
    }

    private AlbumEntity findByIdOrThrowAlbumDataNotFoundException(Long id) {
        return albumRepository.findById(id).orElseThrow(
                () -> new AlbumNotFoundException(ErrorCodes.DATA_NOT_FOUND, ErrorCodes.DATA_NOT_FOUND.getMessage()));
    }
}
