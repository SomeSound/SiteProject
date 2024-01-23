package com.example.hyper.services;

import com.example.hyper.dtos.requests.ArtistRequestDTO;
import com.example.hyper.entities.CustomerEntity;
import com.example.hyper.exceptions.ArtistNotFoundException;
import com.example.hyper.exceptions.CustomerNotFoundException;
import com.example.hyper.exceptions.InvalidArtistDataException;
import com.example.hyper.constants.ErrorCodes;
import com.example.hyper.dtos.responses.pages.ArtistPageResponseDTO;
import com.example.hyper.dtos.responses.ArtistResponseDTO;
//import com.example.hyper.repositories.ArtistRepository;
import com.example.hyper.repositories.ArtistRepository;
import com.example.hyper.repositories.CustomerRepository;
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

    @Autowired
    private final ArtistRepository artistRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final CustomerRepository customerRepository;

    @Override
    public ArtistResponseDTO save(String customerId, ArtistRequestDTO artist) {

        ArtistEntity artistEntity;
        try{
            CustomerEntity customer = findByCustomerIdOrThrowUserDataNotFoundException(customerId);

            artistEntity = modelMapper.map(artist, ArtistEntity.class);
            artistEntity.setCustomer(customer);

            artistEntity = artistRepository.save(artistEntity);

            return modelMapper.map(artistEntity, ArtistResponseDTO.class);
        }catch (DataIntegrityViolationException e){
            throw new InvalidArtistDataException(ErrorCodes.INVALID_ARTIST_ERROR,
                    ErrorCodes.INVALID_ARTIST_ERROR.getMessage());
        }
    }

    @Override
    public ArtistPageResponseDTO find(List<String> names, Pageable pageable) {

        Page<ArtistEntity> artistEntities;

        if(names != null){
            artistEntities = artistRepository.findByUsername(names, pageable);
        } else {
            artistEntities = artistRepository.findAll(pageable);
        }

        return modelMapper.map(artistEntities, ArtistPageResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        ArtistEntity artistCurrent = findByIdOrThrowArtistDataNotFoundException(id);

        artistRepository.delete(artistCurrent);
    }

    private ArtistEntity findByIdOrThrowArtistDataNotFoundException(Long id) {
        return artistRepository.findById(id).orElseThrow(
                () -> new ArtistNotFoundException(ErrorCodes.DATA_NOT_FOUND, ErrorCodes.DATA_NOT_FOUND.getMessage()));
    }

    private CustomerEntity findByCustomerIdOrThrowUserDataNotFoundException(String customerId) {
        return customerRepository.findByCustomerId(customerId).orElseThrow(
                () -> new CustomerNotFoundException(ErrorCodes.DATA_NOT_FOUND, ErrorCodes.DATA_NOT_FOUND.getMessage()));
    }

}
