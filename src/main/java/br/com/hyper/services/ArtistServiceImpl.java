package br.com.hyper.services;

import br.com.hyper.constants.ErrorCodes;
import br.com.hyper.dtos.responses.pages.ArtistPageResponseDTO;
import br.com.hyper.enums.UserRole;
import br.com.hyper.exceptions.ArtistNotFoundException;
import br.com.hyper.exceptions.InvalidArtistDataException;
import br.com.hyper.dtos.requests.ArtistRequestDTO;
import br.com.hyper.entities.CustomerEntity;
import br.com.hyper.exceptions.CustomerException;
import br.com.hyper.dtos.responses.ArtistResponseDTO;
import br.com.hyper.repositories.ArtistRepository;
import br.com.hyper.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.hyper.entities.ArtistEntity;

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
    public ArtistResponseDTO save(ArtistRequestDTO artist) {

        ArtistEntity artistEntity;
        try{
            CustomerEntity customer = findByEmailOrThrowUserDataNotFoundException(artist.getEmail());

            artistEntity = modelMapper.map(artist, ArtistEntity.class);
//            artistEntity.setCustomer(customer);

            artistEntity = artistRepository.save(artistEntity);

            List<ArtistEntity> artists = customer.getArtistProfiles();
            artists.add(artistEntity);

            customer.setRole(UserRole.ARTIST);
            customer.setArtistProfiles(artists);
            customerRepository.save(customer);

            return modelMapper.map(artistEntity, ArtistResponseDTO.class);
        }catch (DataIntegrityViolationException e){
            throw new InvalidArtistDataException(ErrorCodes.DUPLICATED_DATA,
                    ErrorCodes.DUPLICATED_DATA.getMessage());
        }
    }

    @Override
    public ArtistPageResponseDTO find(List<String> username, Pageable pageable) {

        Page<ArtistEntity> artistEntities;

        if(username != null){
            artistEntities = artistRepository.findByUsernamePage(username, pageable);
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

    private CustomerEntity findByEmailOrThrowUserDataNotFoundException(String email) {
        return customerRepository.findByEmail(email).orElseThrow(
                () -> new CustomerException(ErrorCodes.DATA_NOT_FOUND, ErrorCodes.DATA_NOT_FOUND.getMessage()));
    }

}
