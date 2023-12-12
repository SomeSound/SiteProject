package com.example.hyper.services;

import com.example.hyper.constants.ErrorCodes;
import com.example.hyper.dtos.responses.UserPageResponseDTO;
import com.example.hyper.dtos.responses.UserResponseDTO;
import com.example.hyper.entities.CustomerEntity;
import com.example.hyper.exceptions.ArtistNotFoundException;
import com.example.hyper.exceptions.InvalidUserDataException;
import com.example.hyper.repositories.CustomerRepository;
import com.example.hyper.dtos.CustomerDTO;
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
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public UserResponseDTO save(CustomerDTO user) {
        CustomerEntity customerEntity;
        try {
            customerEntity = modelMapper.map(user, CustomerEntity.class);

            customerEntity = customerRepository.save(customerEntity);

            return modelMapper.map(customerEntity, UserResponseDTO.class);
        }catch (DataIntegrityViolationException e){
            throw new InvalidUserDataException(ErrorCodes.INVALID_USER_ERROR,
                    ErrorCodes.INVALID_USER_ERROR.getMessage());
        }
    }

    @Override
    public UserPageResponseDTO find(List<String> names, Pageable pageable) {

        Page<CustomerEntity> userEntities;

        if(names != null){
            userEntities = customerRepository.findByName(names, pageable);
        } else {
            userEntities = customerRepository.findAll(pageable);
        }
        return modelMapper.map(userEntities, UserPageResponseDTO.class);
    }

    @Override
    public UserResponseDTO update(Long id, CustomerDTO user) {
        CustomerEntity userCurrent = findByIdOrThrowUserDataNotFoundException(id);

        userCurrent.setName(user.getName());
        userCurrent.setUsername(user.getUsername());

        customerRepository.save(userCurrent);

        return modelMapper.map(userCurrent, UserResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        CustomerEntity userCurrent = findByIdOrThrowUserDataNotFoundException(id);

        UserResponseDTO response = modelMapper.map(userCurrent, UserResponseDTO.class);
        customerRepository.delete(userCurrent);
    }

    private CustomerEntity findByIdOrThrowUserDataNotFoundException(Long id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new ArtistNotFoundException(ErrorCodes.DATA_NOT_FOUND, ErrorCodes.DATA_NOT_FOUND.getMessage()));
    }

}
