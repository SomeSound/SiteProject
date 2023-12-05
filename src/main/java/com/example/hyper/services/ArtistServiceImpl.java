package com.example.hyper.services;

import com.example.hyper.exceptions.ArtistNotFoundException;
import com.example.hyper.exceptions.InvalidArtistDataException;
import com.example.hyper.constants.ErrorCodes;
import com.example.hyper.dtos.requests.ArtistRequestDTO;
import com.example.hyper.dtos.responses.pages.ArtistPageResponseDTO;
import com.example.hyper.dtos.responses.ArtistResponseDTO;
import com.example.hyper.repositories.ArtistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.hyper.entities.ArtistEntity;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public ArtistResponseDTO save(ArtistRequestDTO artist) {

        ArtistEntity artistEntity;
        try{
            artistEntity = modelMapper.map(artist, ArtistEntity.class);

            artistEntity = artistRepository.save(artistEntity);

            return modelMapper.map(artistEntity, ArtistResponseDTO.class);
        }catch (DataIntegrityViolationException e){
            throw new   InvalidArtistDataException(ErrorCodes.INVALID_ARTIST_ERROR,
                    ErrorCodes.INVALID_ARTIST_ERROR.getMessage());
        }
    }

    @Override
    public ArtistPageResponseDTO find(List<String> names, Pageable pageable) {

        Page<ArtistEntity> artistEntities;

        if(names != null){
            artistEntities = artistRepository.findByName(names, pageable);
        } else {
            artistEntities = artistRepository.findAll(pageable);
        }

        return modelMapper.map(artistEntities, ArtistPageResponseDTO.class);
    }

    @Override
    public ArtistResponseDTO update(Long id, ArtistRequestDTO artist) {
        ArtistEntity artistCurrent = findByIdOrThrowArtistDataNotFoundException(id);

        artistCurrent.setName(artist.getName());
        artistCurrent.setCountry(artist.getCountry());

        artistRepository.save(artistCurrent);

        return modelMapper.map(artistCurrent, ArtistResponseDTO.class);
    }
    @Override
    public void delete(Long id) {
        ArtistEntity artistCurrent = findByIdOrThrowArtistDataNotFoundException(id);

        ArtistResponseDTO response = modelMapper.map(artistCurrent, ArtistResponseDTO.class);
        artistRepository.delete(artistCurrent);
    }

    private ArtistEntity findByIdOrThrowArtistDataNotFoundException(Long id) {
        return artistRepository.findById(id).orElseThrow(
                () -> new ArtistNotFoundException(ErrorCodes.DATA_NOT_FOUND, ErrorCodes.DATA_NOT_FOUND.getMessage()));
    }

}
