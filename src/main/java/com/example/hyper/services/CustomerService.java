package com.example.hyper.services;

import com.example.hyper.dtos.requests.CustomerRequestDTO;
import com.example.hyper.dtos.responses.pages.CustomerPageResponseDTO;
import com.example.hyper.dtos.responses.CustomerResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {

    public CustomerResponseDTO save(CustomerRequestDTO user);

    public CustomerPageResponseDTO find(List<String> names, Pageable pageable);

    public CustomerResponseDTO update(Long id, CustomerRequestDTO user);

    public void delete(Long id);
}
