package com.example.SiteProject.SiteProject.services;

import com.example.SiteProject.SiteProject.constants.ErrorCodes;
import com.example.SiteProject.SiteProject.dtos.ArtistDTO;
import com.example.SiteProject.SiteProject.dtos.MusicDTO;
import com.example.SiteProject.SiteProject.dtos.responses.ArtistPageResponseDTO;
import com.example.SiteProject.SiteProject.dtos.responses.ArtistResponseDTO;
import com.example.SiteProject.SiteProject.dtos.responses.MusicPageResponseDTO;
import com.example.SiteProject.SiteProject.dtos.responses.MusicResponseDTO;
import com.example.SiteProject.SiteProject.entities.ArtistEntity;
import com.example.SiteProject.SiteProject.entities.MusicEntity;
import com.example.SiteProject.SiteProject.exceptions.ArtistNotFoundException;
import com.example.SiteProject.SiteProject.exceptions.InvalidArtistDataException;
import com.example.SiteProject.SiteProject.exceptions.InvalidMusicDataException;
import com.example.SiteProject.SiteProject.exceptions.MusicNotFoundException;
import com.example.SiteProject.SiteProject.repositories.ArtistRepository;
import com.example.SiteProject.SiteProject.repositories.MusicRepository;
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
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public ArtistResponseDTO save(ArtistDTO artist) {

        ArtistEntity artistEntity;
        try{
            artistEntity = modelMapper.map(artist, ArtistEntity.class);

            artistEntity = artistRepository.save(artistEntity);

            return modelMapper.map(artistEntity, ArtistResponseDTO.class);
        }catch (DataIntegrityViolationException e){
            throw new InvalidArtistDataException(ErrorCodes.INVALID_ARTIST_ERROR,
                    ErrorCodes.INVALID_ARTIST_ERROR.getMessage()); //TO REMOVE
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
    public ArtistResponseDTO update(Long id, ArtistDTO artist) {
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
