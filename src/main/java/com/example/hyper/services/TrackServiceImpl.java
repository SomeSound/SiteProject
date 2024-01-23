package com.example.hyper.services;

import com.example.hyper.constants.ErrorCodes;
import com.example.hyper.dtos.responses.AlbumResponseDTO;
import com.example.hyper.dtos.responses.TrackResponseDTO;
import com.example.hyper.entities.AlbumEntity;
import com.example.hyper.entities.ArtistEntity;
import com.example.hyper.entities.CustomerEntity;
import com.example.hyper.exceptions.ArtistNotFoundException;
import com.example.hyper.exceptions.TrackNotFoundException;
import com.example.hyper.dtos.requests.TrackRequestDTO;
import com.example.hyper.dtos.responses.pages.TrackPageResponseDTO;
import com.example.hyper.entities.TrackEntity;
import com.example.hyper.exceptions.CustomerNotFoundException;
import com.example.hyper.repositories.AlbumRepository;
import com.example.hyper.repositories.ArtistRepository;
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

import static com.example.hyper.constants.Constants.SP_ZONE_ID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrackServiceImpl implements TrackService {

    @Autowired
    private final TrackRepository trackRepository;

    @Autowired
    private final ArtistRepository artistRepository;

    @Autowired
    private final AlbumRepository albumRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public AlbumResponseDTO save(TrackRequestDTO track) {

        TrackEntity trackEntity;
        try{
            trackEntity = modelMapper.map(track, TrackEntity.class);

            trackEntity.setPrice(3);

            ArtistEntity artist = findByIdOrThrowArtistDataNotFoundException(track.getArtistId());

            AlbumEntity album = AlbumEntity.builder()
                    .name(trackEntity.getName())
                    .image(trackEntity.getImage())
                    .artist(artist)
                    .releaseDate(ZonedDateTime.now(ZoneId.of(SP_ZONE_ID)))
                    .build();

            trackRepository.save(trackEntity);

            album.setTrackList(List.of(trackEntity));
            AlbumEntity albumEntity = albumRepository.save(album);

            return modelMapper.map(albumEntity, AlbumResponseDTO.class);

        }catch (DataIntegrityViolationException e){
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

    private ArtistEntity findByIdOrThrowArtistDataNotFoundException(Long id) {
        return artistRepository.findById(id).orElseThrow(
                () -> new ArtistNotFoundException(ErrorCodes.DATA_NOT_FOUND, ErrorCodes.DATA_NOT_FOUND.getMessage()));
    }

}
