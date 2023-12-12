package com.example.hyper.services;

import com.example.hyper.dtos.CustomerDTO;
import com.example.hyper.dtos.responses.UserPageResponseDTO;
import com.example.hyper.dtos.responses.UserResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {

    public UserResponseDTO save(CustomerDTO user);

    public UserPageResponseDTO find(List<String> names, Pageable pageable);

    public UserResponseDTO update(Long id, CustomerDTO user);

    public void delete(Long id);
}
