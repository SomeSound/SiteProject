package com.example.hyper.services;

import com.example.hyper.dtos.requests.CustomerRequestDTO;
import com.example.hyper.dtos.responses.pages.CustomerPageResponseDTO;
import com.example.hyper.dtos.responses.CustomerResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {

    CustomerResponseDTO save(CustomerRequestDTO customer);

    CustomerResponseDTO findByCustomerId(String customerId);

    CustomerPageResponseDTO findAll(Pageable pageable);

    CustomerResponseDTO update(Long id, CustomerRequestDTO user);

    void delete(Long id);
}
