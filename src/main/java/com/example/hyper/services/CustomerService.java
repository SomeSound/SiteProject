package com.example.hyper.services;

import com.example.hyper.dtos.CustomerDTO;
import com.example.hyper.dtos.responses.CustomerPageResponseDTO;
import com.example.hyper.dtos.responses.CustomerResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {

    CustomerResponseDTO save(CustomerDTO customer);

    CustomerPageResponseDTO find(List<String> names, Pageable pageable);

    CustomerResponseDTO update(Long id, CustomerDTO user);

    void delete(Long id);
}
