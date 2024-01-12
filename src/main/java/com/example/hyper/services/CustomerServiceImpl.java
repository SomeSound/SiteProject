package com.example.hyper.services;

import com.example.hyper.constants.ErrorCodes;
import com.example.hyper.dtos.requests.CustomerRequestDTO;
import com.example.hyper.dtos.responses.CustomerResponseDTO;
import com.example.hyper.dtos.responses.pages.CustomerPageResponseDTO;
import com.example.hyper.entities.CustomerEntity;
import com.example.hyper.exceptions.ArtistNotFoundException;
import com.example.hyper.exceptions.InvalidCustomerDataException;
import com.example.hyper.repositories.CustomerRepository;
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
    public CustomerResponseDTO save(CustomerRequestDTO user) {
        CustomerEntity customerEntity;
        try {
            customerEntity = modelMapper.map(user, CustomerEntity.class);

            customerEntity = customerRepository.save(customerEntity);

            return modelMapper.map(customerEntity, CustomerResponseDTO.class);
        }catch (DataIntegrityViolationException e){
            throw new InvalidCustomerDataException(ErrorCodes.INVALID_USER_ERROR,
                    ErrorCodes.INVALID_USER_ERROR.getMessage());
        }
    }

    @Override
    public CustomerPageResponseDTO find(List<String> names, Pageable pageable) {

        Page<CustomerEntity> userEntities;

        if(names != null){
            userEntities = customerRepository.findByName(names, pageable);
        } else {
            userEntities = customerRepository.findAll(pageable);
        }
        return modelMapper.map(userEntities, CustomerPageResponseDTO.class);
    }

    @Override
    public CustomerResponseDTO update(Long id, CustomerRequestDTO customer) {
        CustomerEntity customerCurrent = findByIdOrThrowCustomerDataNotFoundException(id);

        customerCurrent.setName(customer.getName());
        customerCurrent.setUsername(customer.getUsername());

        customerRepository.save(customerCurrent);

        return modelMapper.map(customerCurrent, CustomerResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        CustomerEntity customerCurrent = findByIdOrThrowCustomerDataNotFoundException(id);

        CustomerResponseDTO response = modelMapper.map(customerCurrent, CustomerResponseDTO.class);
        customerRepository.delete(customerCurrent);
    }

    private CustomerEntity findByIdOrThrowCustomerDataNotFoundException(Long id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new ArtistNotFoundException(ErrorCodes.DATA_NOT_FOUND, ErrorCodes.DATA_NOT_FOUND.getMessage()));
    }

}