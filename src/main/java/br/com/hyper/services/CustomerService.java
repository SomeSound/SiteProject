package br.com.hyper.services;

import br.com.hyper.dtos.requests.LoginRequestDTO;
import br.com.hyper.dtos.requests.CustomerRequestDTO;
import br.com.hyper.dtos.responses.CustomerResponseDTO;
import br.com.hyper.dtos.responses.LoginResponseDTO;
import br.com.hyper.dtos.responses.TokenResponseDTO;
import br.com.hyper.dtos.responses.pages.CustomerPageResponseDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    CustomerResponseDTO save(CustomerRequestDTO customer);

    LoginResponseDTO login(LoginRequestDTO authentication, HttpServletResponse http);

    TokenResponseDTO refreshToken(String token);

    CustomerResponseDTO findByEmail(String email);

    CustomerPageResponseDTO findAll(Pageable pageable);

    CustomerResponseDTO update(Long id, CustomerRequestDTO user);

    void delete(Long id);
}
