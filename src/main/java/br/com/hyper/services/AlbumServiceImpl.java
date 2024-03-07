package br.com.hyper.services;

import br.com.hyper.constants.ErrorCodes;
import br.com.hyper.dtos.requests.AlbumRequestDTO;
import br.com.hyper.dtos.responses.AlbumResponseDTO;
import br.com.hyper.dtos.responses.pages.AlbumPageReponseDTO;
import br.com.hyper.entities.AlbumEntity;
import br.com.hyper.exceptions.AlbumNotFoundException;
import br.com.hyper.exceptions.InvalidAlbumDataException;
import br.com.hyper.repositories.AlbumRepository;
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
    private final AlbumRepository albumRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public AlbumResponseDTO save(AlbumRequestDTO album) {

        AlbumEntity albumEntity;
        try{
            albumEntity = modelMapper.map(album, AlbumEntity.class);

            albumEntity = albumRepository.save(albumEntity);

            return modelMapper.map(albumEntity, AlbumResponseDTO.class);

        }catch (DataIntegrityViolationException e){
            throw new InvalidAlbumDataException(ErrorCodes.DUPLICATED_DATA,
                    ErrorCodes.DUPLICATED_DATA.getMessage());
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
