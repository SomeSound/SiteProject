package com.example.hyper.services;

import com.example.hyper.constants.ErrorCodes;
import com.example.hyper.dtos.responses.pages.CustomerPageResponseDTO;
import com.example.hyper.dtos.responses.CustomerResponseDTO;
import com.example.hyper.entities.CustomerEntity;
import com.example.hyper.exceptions.ArtistNotFoundException;
import com.example.hyper.exceptions.InvalidUserDataException;
import com.example.hyper.repositories.CustomerRepository;
import com.example.hyper.dtos.requests.CustomerRequestDTO;
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

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public CustomerResponseDTO save(CustomerRequestDTO customer) {
        CustomerEntity customerEntity;
        try {
            customerEntity = modelMapper.map(customer, CustomerEntity.class);
            customerEntity = customerRepository.save(customerEntity);

            return modelMapper.map(customerEntity, CustomerResponseDTO.class);
        }catch (DataIntegrityViolationException e){
            throw new InvalidUserDataException(ErrorCodes.INVALID_USER_ERROR,
                    ErrorCodes.INVALID_USER_ERROR.getMessage());
        }
    }

    @Override
    public CustomerPageResponseDTO find(List<String> names, Pageable pageable) {

        Page<CustomerEntity> customerEntities;

        if(names != null){
            customerEntities = customerRepository.findByName(names, pageable);
        } else {
            customerEntities = customerRepository.findAll(pageable);
        }
        return modelMapper.map(customerEntities, CustomerPageResponseDTO.class);
    }

    @Override
    public CustomerResponseDTO update(Long id, CustomerRequestDTO user) {
        CustomerEntity userCurrent = findByIdOrThrowUserDataNotFoundException(id);

        userCurrent.setName(user.getName());
        userCurrent.setUsername(user.getUsername());

        customerRepository.save(userCurrent);

        return modelMapper.map(userCurrent, CustomerResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        CustomerEntity userCurrent = findByIdOrThrowUserDataNotFoundException(id);

        CustomerResponseDTO response = modelMapper.map(userCurrent, CustomerResponseDTO.class);
        customerRepository.delete(userCurrent);
    }

    private CustomerEntity findByIdOrThrowUserDataNotFoundException(Long id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new ArtistNotFoundException(ErrorCodes.DATA_NOT_FOUND, ErrorCodes.DATA_NOT_FOUND.getMessage()));
    }

}
