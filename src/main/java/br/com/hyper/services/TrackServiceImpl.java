package br.com.hyper.services;

import br.com.hyper.constants.Constants;
import br.com.hyper.dtos.responses.pages.TrackPageResponseDTO;
import br.com.hyper.entities.AlbumEntity;
import br.com.hyper.enums.Genre;
import br.com.hyper.exceptions.ArtistNotFoundException;
import br.com.hyper.exceptions.InvalidAlbumDataException;
import br.com.hyper.exceptions.InvalidArtistDataException;
import br.com.hyper.exceptions.TrackNotFoundException;
import br.com.hyper.repositories.CustomerRepository;
import br.com.hyper.repositories.TrackRepository;
import br.com.hyper.constants.ErrorCodes;
import br.com.hyper.dtos.responses.AlbumResponseDTO;
import br.com.hyper.dtos.responses.TrackResponseDTO;
import br.com.hyper.entities.ArtistEntity;
import br.com.hyper.dtos.requests.TrackRequestDTO;
import br.com.hyper.entities.TrackEntity;
import br.com.hyper.repositories.AlbumRepository;
import br.com.hyper.repositories.ArtistRepository;
import br.com.hyper.utils.S3Uploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrackServiceImpl implements TrackService {

    @Autowired
    private final TrackRepository trackRepository;

    @Autowired
    private final ArtistRepository artistRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final S3Uploader s3Uploader;

    @Override
    public TrackResponseDTO save(TrackRequestDTO track, MultipartFile file, Long artistId) {

        ArtistEntity artist = findByIdOrThrowArtistDataNotFoundException(artistId);
        TrackEntity trackEntity = TrackEntity.builder()
                .name(track.getName())
                .duration(file.getSize())
                .price(3)
                .genre(Genre.valueOf(track.getGenre()))
                .image(track.getImage())
                .artist(artist)
                .build();

        s3Uploader.saveTrackOnBucket(artist.getUsername(), Genre.valueOf(track.getGenre()), file);

        trackRepository.save(trackEntity);

        return modelMapper.map(trackEntity, TrackResponseDTO.class);

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
                () -> new TrackNotFoundException(ErrorCodes.DATA_NOT_FOUND, ErrorCodes.DATA_NOT_FOUND.getMessage()));
    }

}
