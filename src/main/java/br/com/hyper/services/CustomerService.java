package br.com.hyper.services;

import br.com.hyper.dtos.requests.AuthenticationDTO;
import br.com.hyper.dtos.requests.CustomerRequestDTO;
import br.com.hyper.dtos.responses.CustomerResponseDTO;
import br.com.hyper.dtos.responses.TokenResponseDTO;
import br.com.hyper.dtos.responses.pages.CustomerPageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    CustomerResponseDTO save(CustomerRequestDTO customer);

    TokenResponseDTO login(AuthenticationDTO authentication);

    CustomerResponseDTO findByCustomerId(String customerId);

    CustomerPageResponseDTO findAll(Pageable pageable);

    CustomerResponseDTO update(Long id, CustomerRequestDTO user);

    void delete(Long id);
}
