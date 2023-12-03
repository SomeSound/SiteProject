package com.example.hyper.services;

import com.example.hyper.constants.ErrorCodes;
import com.example.hyper.dtos.requests.CollectionRequestDTO;
import com.example.hyper.dtos.responses.CollectionResponseDTO;
import com.example.hyper.dtos.responses.pages.CollectionPageResponseDTO;
import com.example.hyper.dtos.responses.pages.PlaylistPageReponseDTO;
import com.example.hyper.entities.CollectionEntity;
import com.example.hyper.entities.PlaylistEntity;
import com.example.hyper.exceptions.CollectionNotFoundException;
import com.example.hyper.repositories.CollectionRepository;
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
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository collectionRepository;

    @Autowired
    private final ModelMapper modelMapper;


    @Override
    public CollectionService save(CollectionRequestDTO collection) {

        CollectionEntity collectionEntity;
        try {
            collectionEntity = modelMapper.map(collection, CollectionEntity.class);

            collectionEntity = collectionRepository.save(collectionEntity);

            return modelMapper.map(collectionEntity, CollectionService.class);

        } catch (DataIntegrityViolationException e) {
            return null; // implementar erro
        }
    }
    @Override
    public CollectionPageResponseDTO find(String collection, Pageable pageable) {

        Page<PlaylistEntity> playlistEntities;

        if(collection != null){
            playlistEntities = collectionRepository.findByName(collection, pageable);
        } else {
            playlistEntities = collectionRepository.findAll(pageable);
        }

        return modelMapper.map(playlistEntities, PlaylistPageReponseDTO.class);
    }

    @Override
    public CollectionResponseDTO update(Long id, CollectionRequestDTO collection) {
        return null;
    }

    @Override
    public void delete(Long id) {
        CollectionEntity collectionCurrent = findByIdOrThrowCollectionDataNotFoundException(id);

        CollectionResponseDTO response = modelMapper.map(collectionCurrent, CollectionResponseDTO.class);
        collectionRepository.delete(collectionCurrent);
    }
    private CollectionEntity findByIdOrThrowCollectionDataNotFoundException(Long id) {
        return collectionRepository.findById(id).orElseThrow(
                () -> new CollectionNotFoundException(ErrorCodes.DATA_NOT_FOUND, ErrorCodes.DATA_NOT_FOUND.getMessage()));
    }
}
