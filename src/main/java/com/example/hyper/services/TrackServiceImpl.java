package com.example.hyper.services;

import com.example.hyper.constants.ErrorCodes;
import com.example.hyper.dtos.responses.TrackResponseDTO;
import com.example.hyper.entities.AlbumEntity;
import com.example.hyper.exceptions.TrackNotFoundException;
import com.example.hyper.dtos.requests.TrackRequestDTO;
import com.example.hyper.dtos.responses.pages.TrackPageResponseDTO;
import com.example.hyper.entities.TrackEntity;
import com.example.hyper.repositories.AlbumRepository;
import com.example.hyper.repositories.CustomerRepository;
import com.example.hyper.repositories.TrackRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.hyper.constants.Constants.SP_ZONE_ID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrackServiceImpl implements TrackService {

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TrackResponseDTO save(TrackRequestDTO track) {

        TrackEntity trackEntity;
        try{
            trackEntity = modelMapper.map(track, TrackEntity.class);

            AlbumEntity album = AlbumEntity.builder()
                    .name(trackEntity.getName())
                    .image(trackEntity.getImage())
                    .customerId(customerRepository.getByCustomerId(track.getCustomerId()))
                    .releaseDate(ZonedDateTime.now(ZoneId.of(SP_ZONE_ID)))
                    .build();

            AlbumEntity albumEntity = albumRepository.save(album);

            trackEntity.setAlbumId(albumEntity);
            trackEntity = trackRepository.save(trackEntity);

            return modelMapper.map(trackEntity, TrackResponseDTO.class);

        }catch (DataIntegrityViolationException e){
            System.out.println(e.getMessage());
            return null; // Implementar exception
        }

    }

    @Override
    public TrackPageResponseDTO find(List<String> genres, Pageable pageable) {

        Page<TrackEntity> trackEntities;

        if(genres != null){
            trackEntities = trackRepository.findByGenre(genres, pageable);
        } else {
            trackEntities = trackRepository.findAll(pageable);
        }

        return modelMapper.map(trackEntities, TrackPageResponseDTO.class);
    }

    @Override
    public TrackResponseDTO update(Long id, TrackRequestDTO track) {
        TrackEntity trackCurrent = findByIdOrThrowTrackDataNotFoundException(id);

        trackCurrent.setName(track.getName());
        trackCurrent.setDuration(track.getDuration());

        trackRepository.save(trackCurrent);

        return modelMapper.map(trackCurrent, TrackResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        TrackEntity musicCurrent = findByIdOrThrowTrackDataNotFoundException(id);

        trackRepository.delete(musicCurrent);
    }

    private TrackEntity findByIdOrThrowTrackDataNotFoundException(Long id) {
        return trackRepository.findById(id).orElseThrow(
                () -> new TrackNotFoundException(ErrorCodes.DATA_NOT_FOUND, ErrorCodes.DATA_NOT_FOUND.getMessage()));
    }

}
